Operation parameter
- assume the compiled output as XXX.jar
- not provide anything assume today 9:00AM as countdown 
```aidl
java -jar XXX.jar
```
- provide shift in sec and assume 9:00AM as countdown
```aidl
java -Dshift=-20 -jar XXX.jar 
```
- provide time only, assume today
```aidl
java -Dtime=15:00 -jar XXX.jar 
```
- provide countdown datetime from now
```aidl
java -Ddatetime="2020-01-31 15:00" -jar XXX.jar 
```
- if both time and datetime provide the system will follow the datetime

Build Method
```aidl
mvn clean package
```
after build provide the target will located at /target/XXXX-with-dependencies.jar
