package ge.jibo.util.file.manager.lineExporter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class LogHelper {

  FileOutputStream outputStream;

  public LogHelper(FileOutputStream outputStream) {
    this.outputStream = outputStream;
  }

  public void writeIntoFile(FileOutputStream out, String text) {
    try {
      out.write(text.getBytes());
      out.write(10);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void logRequestDetails(Configuration conf) {
    System.out.println(new Date() + " - *** LineExporter Started ***");
    writeIntoFile(outputStream, new Date() + " - *** LineExporter Started ***");

    System.out.println(new Date() + " - inputFilePath: " + conf.getInFilePath());
    writeIntoFile(outputStream, new Date() + " - inputFilePath: " + conf.getInFilePath());

    System.out.println(new Date() + " - outFilePath: " + conf.getOutFilePath());
    writeIntoFile(outputStream, new Date() + " - outFilePath: " + conf.getOutFilePath());

    System.out.println(new Date() + " - logFilePath: " + conf.getLogFilePath());
    writeIntoFile(outputStream, new Date() + " - logFilePath: " + conf.getLogFilePath());

    if (conf.getRequestType() == RequestType.Remove) {
      System.out.println(new Date() + " - Request: Export all line, except the line which contains '" + conf.getQueryText() + "' text");
      writeIntoFile(outputStream,
          new Date() + " - Request: Export all line, except the line which contains '" + conf.getQueryText() + "' text");
    } else if (conf.getRequestType() == RequestType.Search) {
      System.out.println(new Date() + " - Request: Export all line which contains '" + conf.getQueryText() + "' text");
      writeIntoFile(outputStream, new Date() + " - Request: Export all line which contains '" + conf.getQueryText() + "' text");
    }
  }
}
