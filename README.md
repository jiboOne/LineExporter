LineExporter
=======

Simple Text File manager written in Java, basic features:

* Read files and Export nessesery lines.
* Read large files efectivelly.

**How to run**:

Following command starts application:
 
    java -jar lineExporter.jar "S" "queryText" "D:\logs\input" "D:\logs\output" "D:\logs\log"
    java -jar lineExporter.jar "R" "queryText" "D:\logs\input" "D:\logs\output" "D:\logs\log"


* **S** - export all line which contains "some queryText"
* **R** - export all line which does not contains "some queryText"
* **queryText** - any text you want to searchin file/files
* **D:\logs\input** - input file address, parameter is optional (default value same directory)
* **D:\logs\output** - output file address, parameter is optional (default value same directory)
* **D:\logs\log** - log file address, parameter is optional (default value same directory)

Obviously Java (at least 1.8) is needed to run the application.

**How to compile**:

Maven 3.x is needed to build the project and create distribution package.
 
    mvn clean install
