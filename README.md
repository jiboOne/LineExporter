LineExporter
=======

Simple Text File manager written in Java, basic features:

* Read files and export required lines
* Read large files efectivelly

**How to run**:

Following command starts application:
 
    java -jar lineExporter-0.0.1.jar "S" "queryText" "inputFile" "outputFile" "logFile"
    java -jar lineExporter-0.0.1.jar "R" "queryText" "inputFile" "outputFile" "logFile"


* **S** - export all line which contains "some queryText"
* **R** - export all line which does not contains "some queryText"
* **queryText** - any text you want to searchin file/files
* **inputFile** - input file address example: *D:\logs\input.txt*, parameter is optional (default value is same directory)
* **outputFile** - output file address example: *D:\logs\outout.txt*, parameter is optional (default value is same directory)
* **logFile** - log file address example: *D:\logs\log.txt*, parameter is optional (default value is same directory)

Obviously Java (at least 1.8) is needed to run the application.

**How to compile**:

Maven 3.x is needed to build the project and create distribution package.
 
    mvn clean install
