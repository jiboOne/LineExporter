package ge.jibo.util.file.manager.lineExporter;

public class Configuration {
  private String inFilePath;
  private String outFilePath;
  private String logFilePath;
  private String searchText;
  private RequestType requestType;

  Configuration(final String[] args) {
    if (args.length > 0 && args[0] != null && !args[0].isEmpty()) {
      this.requestType = RequestType.Search;
      if (args[0].equals("R")) {
        this.requestType = RequestType.Remove;
      }
    } else {
      this.requestType = RequestType.Search;
    }

    if (args.length > 1 && args[1] != null && !args[1].isEmpty()) {
      this.searchText = args[1];
    } else {
      this.searchText = "command = c3";
    }

    if (args.length > 2 && args[2] != null && !args[2].isEmpty()) {
      this.inFilePath = args[2];
    } else {
      this.inFilePath = ".";
    }
    if (args.length > 3 && args[3] != null && !args[3].isEmpty()) {
      this.outFilePath = args[3];
    } else {
      this.outFilePath = ".";
    }
    if (args.length > 4 && args[4] != null && !args[4].isEmpty()) {
      this.logFilePath = args[4];
    } else {
      this.logFilePath = ".";
    }
  }

  String getInFilePath() {
    return this.inFilePath;
  }

  public void setInFilePath(final String inFilePath) {
    this.inFilePath = inFilePath;
  }

  String getOutFilePath() {
    return this.outFilePath;
  }

  public void setOutFilePath(final String outFilePath) {
    this.outFilePath = outFilePath;
  }

  public String getQueryText() {
    return this.searchText;
  }

  public void setSearchText(final String searchText) {
    this.searchText = searchText;
  }

  public String getLogFilePath() {
    return logFilePath;
  }

  public void setLogFilePath(String logFilePath) {
    this.logFilePath = logFilePath;
  }

  public RequestType getRequestType() {
    return requestType;
  }

  public void setRequestType(RequestType requestType) {
    this.requestType = requestType;
  }
}
