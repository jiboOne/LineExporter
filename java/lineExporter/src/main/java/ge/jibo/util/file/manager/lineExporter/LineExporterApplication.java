package ge.jibo.util.file.manager.lineExporter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class LineExporterApplication {

  public static void writeIntoFile(FileOutputStream outputStream, String text) {
    try {
      outputStream.write(text.getBytes());
      outputStream.write(10);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void logRequestDetails(FileOutputStream log, Configuration conf) {
    System.out.println(new Date() + " - *** LineExporter Started ***");
    writeIntoFile(log, new Date() + " - *** LineExporter Started ***");

    System.out.println(new Date() + " - inputFilePath: " + conf.getInFilePath());
    writeIntoFile(log, new Date() + " - inputFilePath: " + conf.getInFilePath());

    System.out.println(new Date() + " - outFilePath: " + conf.getOutFilePath());
    writeIntoFile(log, new Date() + " - outFilePath: " + conf.getOutFilePath());

    System.out.println(new Date() + " - logFilePath: " + conf.getLogFilePath());
    writeIntoFile(log, new Date() + " - logFilePath: " + conf.getLogFilePath());

    if (conf.getRequestType() == RequestType.Remove) {
      System.out.println(new Date() + " - Request: Export all line, except the line which contains '" + conf.getQueryText() + "' text");
      writeIntoFile(log, new Date() + " - Request: Export all line, except the line which contains '" + conf.getQueryText() + "' text");
    } else if (conf.getRequestType() == RequestType.Search) {
      System.out.println(new Date() + " - Request: Export all line which contains '" + conf.getQueryText() + "' text");
      writeIntoFile(log, new Date() + " - Request: Export all line which contains '" + conf.getQueryText() + "' text");
    }
  }

  public static void main(String[] args) throws IOException {
    SpringApplication.run(LineExporterApplication.class, args);
    Configuration conf = new Configuration(args);
    try (Stream<Path> paths = Files.walk(Paths.get(conf.getInFilePath()))) {
      final List<String> listOfFilePaths = paths.filter(Files::isRegularFile)
                                                .map(Path::toAbsolutePath)
                                                .map(Path::toString)
                                                .collect(Collectors.toList());
      final FileOutputStream log = new FileOutputStream(conf.getLogFilePath() + "/log.txt");

      logRequestDetails(log, conf);

      BufferedReader fileBufferReader;
      FileOutputStream outputStream;
      File file = null;
      Scanner sc = null;
      String line;

      for (String filePath : listOfFilePaths) {
        fileBufferReader = null;
        outputStream = null;
        if (!filePath.contains(".jar") && !filePath.contains("log.txt")) {
          writeIntoFile(log, new Date() + " - Parsing File: " + filePath);

          String fileName = getFileName(filePath);

          outputStream = new FileOutputStream(conf.getOutFilePath() + fileName + "_exported.txt");
          fileBufferReader = new BufferedReader(new FileReader(filePath));

          try {
            System.out.println(new Date() + " - Line Export Started For " + filePath + " file");
            writeIntoFile(log, new Date() + " - Line Export Started For " + filePath + " file");
            if (conf.getQueryText() != null && conf.getRequestType() == RequestType.Remove) {
              while ((line = fileBufferReader.readLine()) != null) {
                if (conf.getQueryText() != null && !line.contains(conf.getQueryText())) {
                  writeIntoFile(outputStream, line);
                }
              }
            } else if (conf.getQueryText() != null && conf.getRequestType() == RequestType.Search) {
              while ((line = fileBufferReader.readLine()) != null) {
                if (line.contains(conf.getQueryText())) {
                  writeIntoFile(outputStream, line);
                }
              }
            }
          } finally {
            System.out.println(new Date() + " - Line Export Finished for " + filePath + " file");
            writeIntoFile(log, (new Date() + " - Line Export Finished for " + filePath + " file"));
            fileBufferReader.close();
            outputStream.close();
          }
        }
      }
      System.out.println(new Date() + " - *** LineExporter Finished ***");
      writeIntoFile(log, (new Date() + " - *** LineExporter Started ***"));
    }
  }

  private static String getFileName(String filePath) {
    String fileName = "";
    if (filePath.contains(".") && filePath.contains("\\") && filePath.lastIndexOf("\\") < filePath.lastIndexOf(".")) {
      fileName = filePath.substring(filePath.lastIndexOf("\\"), filePath.lastIndexOf("."));
    }
    return fileName;
  }
}
