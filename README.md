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
default file name is index.yaml, if you want to change it use -f switch
### sample swagger ui use

```
java -jar yaml-merge.jar --input /home/user/example --server 9090  --serverDocumentRoot /path/to/swagger/dist --serverFilename swagger.yaml
```
then

[http://localhost:9090/?url=http://localhost:9090/swagger.yaml](http://localhost:9090/?url=http://localhost:9090/swagger.yaml)

### output from console when wrong parameters are entered
```
Usage:
 --extension (-e) yaml                  : Extension which we should look for
 --input (-i) <folder>                  : Path to source yaml files.
 --output (-o) <file>.yaml              : Where the result should be stored
 --server (-s) <server port>            : Server port where it should start.
 --serverDocumentRoot (-r)              : Folder which we should serve files
 <docRootFolder>                          from.
 --serverFilename (-f) index.yaml       : Filename of generated yaml file.
```

## future
- make serving file(s) more configurable
- add shell scripts (to make life easier)
- add tests