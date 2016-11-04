# simple yaml merge
I use this project for creating [swagger](http://swagger.io) yaml documentation.
You can use it to simply make one yaml file from multiple or to serve created yaml file with simple embedded server.

Simple yaml files are in example directory.
## creating output file from sources
```
java -jar yaml-merge.jar --input /home/user/example --output /home/user/example.yaml
```
## serving file from webserver
```
java -jar yaml-merge.jar --input /home/user/example --server 9090
```
right now the server serves always created yaml file regardless the path. This will be changed in future, because I want to serve it static files too.
### sample swagger ui use
given code above
```
http://petstore.swagger.io/?url=http://localhost:9090/swagger.yaml
```
### output from console when wrong parameters are entered
```
Usage:
 --extension (-e) yaml       : Extension which we should look for
 --input (-i) <folder>       : Path to source yaml files.
 --output (-o) <file>.yaml   : Where the result should be stored
 --server (-s) <server port> : Server port where it should start.
```

## future
- make serving file(s) more configurable
- add shell scripts (to make life easier)
- add tests
