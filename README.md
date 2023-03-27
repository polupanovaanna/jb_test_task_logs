### A safe pattern matching tool

RegexpMatcher class can handle all exceptions in pattern matching process. All output is translated into logs.

It also handles long pattern matching. If it works longer than required (3 seconds), the process will be safely interrupted and 
```matches``` function will return false.

There are tests that could be runned as ```./gradlew test```