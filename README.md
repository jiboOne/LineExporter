LineExporter
=======

Simple Text File manager written in Java, basic features:

* Read files and Export nessesery lines.
* Read large files efectivelly.

**How to run**:

Following command starts application:
 
    java -jar lineExporter.jar "S" "some queryText" "D:\logs\input" "D:\logs\output" "D:\logs\log"
    java -jar lineExporter.jar "R" "some queryText" "D:\logs\input" "D:\logs\output" "D:\logs\log"

First param is **Request Types**
**S** - export all line which contains "some queryText"
**R** - export all line which does not contains "some queryText"

Obviously Java (at least 1.8) is needed to run the application.

**How to compile**:

Maven 3.x is needed to build the project and create distribution package.
 
    mvn clean install
