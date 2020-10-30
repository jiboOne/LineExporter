package ge.jibo.util.file.manager.lineExporter;

import org.slf4j.Logger;
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

  final static LogHelper logger = new LogHelper();

  public static void main(String[] args) throws IOException {
    SpringApplication.run(LineExporterApplication.class, args);
    Configuration conf = new Configuration(args);
    try (Stream<Path> paths = Files.walk(Paths.get(conf.getInFilePath()))) {
      final List<String> listOfFilePaths = paths.filter(Files::isRegularFile)
                                                .map(Path::toAbsolutePath)
                                                .map(Path::toString)
                                                .collect(Collectors.toList());

      final FileOutputStream log = new FileOutputStream(conf.getLogFilePath() + "/log.txt");
      logger.logRequestDetails(log, conf);

      BufferedReader fileBufferReader;
      FileOutputStream outputStream;
      String line;

      for (String filePath : listOfFilePaths) {
        if (!filePath.contains(".jar") && !filePath.contains("log.txt")) {
          logger.writeIntoFile(log, new Date() + " - Parsing File: " + filePath);

          String fileName = getFileName(filePath);

          outputStream = new FileOutputStream(conf.getOutFilePath() + fileName + "_exported.txt");
          fileBufferReader = new BufferedReader(new FileReader(filePath));

          try {
            System.out.println(new Date() + " - Line Export Started For " + filePath + " file");
            logger.writeIntoFile(log, new Date() + " - Line Export Started For " + filePath + " file");
            if (conf.getQueryText() != null && conf.getRequestType() == RequestType.Remove) {
              while ((line = fileBufferReader.readLine()) != null) {
                if (conf.getQueryText() != null && !line.contains(conf.getQueryText())) {
                  logger.writeIntoFile(outputStream, line);
                }
              }
            } else if (conf.getQueryText() != null && conf.getRequestType() == RequestType.Search) {
              while ((line = fileBufferReader.readLine()) != null) {
                if (line.contains(conf.getQueryText())) {
                  logger.writeIntoFile(outputStream, line);
                }
              }
            }
          } finally {
            System.out.println(new Date() + " - Line Export Finished for " + filePath + " file");
            logger.writeIntoFile(log, (new Date() + " - Line Export Finished for " + filePath + " file"));
            fileBufferReader.close();
            outputStream.close();
          }
        }
      }
      System.out.println(new Date() + " - *** LineExporter Finished ***");
      logger.writeIntoFile(log, (new Date() + " - *** LineExporter Started ***"));
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
