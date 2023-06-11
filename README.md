# smallsteps

attempted with
- Java 11.0.13
- Windows 10

## [1] Hello Java!

```sh
java Hello.java
# => Hello, Java!

javac Hello.java
java -classpath . Hello
# => Hello, Java!

java Hello.java 
# => error: class found on application class path: Hello
# reason https://stackoverflow.com/a/8227712/8812880

java Hello
# => Hello, Java!
```

## [2] My friend can HelpMe
```sh
# new file HelpMe.java, Hello uses HelpMe

javac Hello.java HelpMe.java
java Hello
# => Greetings, Java
```

## [2.5] preparing helper from another world
```sh
# install babashka
bash < <(curl -s https://raw.githubusercontent.com/babashka/babashka/master/install)

# run lotto.clj, a mock server for the example for https://rest-assured.io/ homepage
bb -e "(load-string (slurp \"https://gist.githubusercontent.com/keychera/75128c639bd9c21bc7e079cd00d858f6/raw/25fd1643aa226cccb9f933c682f289716516cf1c/lotto.clj\"))"
```

## [3] I can RESTAssured for now

Let's use the library RESTAssured to test the mock server we have

copying example code from 

```java
when().
    get("/lotto/{id}", 5).
then().
    statusCode(200).
    body("lotto.lottoId", equalTo(5),
         "lotto.winners.winnerId", hasItems(23, 54));
```

add some import statics specified [here](https://github.com/rest-assured/rest-assured/wiki/Usage#static-imports) in our Hello.java
```java
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
```

Then
```sh
# google: "RESTAssured mvn"
# https://mvnrepository.com/artifact/io.rest-assured
# first link
# https://mvnrepository.com/artifact/io.rest-assured/rest-assured
# current latest version > 5.3.0
# https://mvnrepository.com/artifact/io.rest-assured/rest-assured/5.3.0
# Files > bundle (727 kb) > click > a .jar file is downloaded
# put it in this folder

# or 
# Files > bundle (727 kb) > copy link instead of click
# download via curl
curl https://repo1.maven.org/maven2/io/rest-assured/rest-assured/5.3.0/rest-assured-5.3.0.jar --output rest-assured-5.3.0.jar

# with this knowledge https://stackoverflow.com/q/10056895/8812880
javac -classpath ".;rest-assured-5.3.0.jar" Hello.java
# Hello.java:3: error: package org.hamcrest does not exist
# import static org.hamcrest.Matchers.*;
#                           ^

# makes sense, let's also download hamcrest Matches using the same steps as above
# this time it's Files > jar (120 kb)
curl https://repo1.maven.org/maven2/org/hamcrest/hamcrest/2.2/hamcrest-2.2.jar --output hamcrest-2.2.jar

# then
javac -classpath ".;rest-assured-5.3.0.jar;hamcrest-2.2.jar" Hello.java
java -classpath ".;rest-assured-5.3.0.jar;hamcrest-2.2.jar" Hello
# Greetings, Java
# Exception in thread "main" java.lang.NoClassDefFoundError: groovy/lang/GroovyObject
#         at java.base/java.lang.ClassLoader.defineClass1(Native Method)

# interesting, what is going on here?
# but it makes sense when we see the `Compile Dependencies (20)` section
# in the RestAssured mvn page here https://mvnrepository.com/artifact/io.rest-assured/rest-assured/5.3.0
# one of them is `org.apache.groovy » groovy`
# let's download it as well!
curl https://repo1.maven.org/maven2/org/apache/groovy/groovy/4.0.12/groovy-4.0.12.jar --output groovy-4.0.12.jar

# then
javac -classpath ".;rest-assured-5.3.0.jar;hamcrest-2.2.jar" Hello.java
java -classpath ".;rest-assured-5.3.0.jar;hamcrest-2.2.jar;groovy-4.0.12.jar" Hello
# Greetings, Java
# Exception in thread "main" java.lang.NoClassDefFoundError: org/apache/http/client/HttpClient

# again! now let's get them and some others that cause some more subsequent error everytime we run `java -classpath ...`
# note: this time we didn't rerun `javac -classpath`
# also the way we find the needed .jar is to google the class name and then add the keyword `mvn`
# e.g `org/apache/commons/lang3/Validate mvn`
curl https://repo1.maven.org/maven2/org/apache/httpcomponents/httpclient/4.5.13/httpclient-4.5.13.jar --output httpclient-4.5.13.jar
java -classpath ".;rest-assured-5.3.0.jar;hamcrest-2.2.jar;groovy-4.0.12.jar;httpclient-4.5.13.jar" Hello
# Greetings, Java
# Exception in thread "main" java.lang.NoClassDefFoundError: org/apache/http/entity/mime/HttpMultipartMode

curl https://repo1.maven.org/maven2/org/apache/httpcomponents/httpmime/4.5.14/httpmime-4.5.14.jar --output httpmime-4.5.14.jar
java -classpath ".;rest-assured-5.3.0.jar;hamcrest-2.2.jar;groovy-4.0.12.jar;httpclient-4.5.13.jar;httpmime-4.5.14.jar" Hello
# Greetings, Java
# Exception in thread "main" java.lang.NoClassDefFoundError: org/apache/commons/lang3/Validate

curl https://repo1.maven.org/maven2/org/apache/commons/commons-lang3/3.12.0/commons-lang3-3.12.0.jar --output commons-lang3-3.12.0.jar
java -classpath ".;rest-assured-5.3.0.jar;hamcrest-2.2.jar;groovy-4.0.12.jar;httpclient-4.5.13.jar;httpmime-4.5.14.jar;commons-lang3-3.12.0.jar" Hello
# Greetings, Java
# Exception in thread "main" java.lang.NoClassDefFoundError: io/restassured/path/json/mapper/factory/JsonbObjectMapperFactory

curl https://repo1.maven.org/maven2/io/rest-assured/json-path/5.3.0/json-path-5.3.0.jar --output json-path-5.3.0.jar
java -classpath ".;rest-assured-5.3.0.jar;hamcrest-2.2.jar;groovy-4.0.12.jar;httpclient-4.5.13.jar;httpmime-4.5.14.jar;commons-lang3-3.12.0.jar;json-path-5.3.0.jar" Hello
# Greetings, Java
# Exception in thread "main" java.lang.NoClassDefFoundError: io/restassured/path/xml/mapper/factory/JakartaEEObjectMapperFactory

curl https://repo1.maven.org/maven2/io/rest-assured/xml-path/5.3.0/xml-path-5.3.0.jar --output xml-path-5.3.0.jar
java -classpath ".;rest-assured-5.3.0.jar;hamcrest-2.2.jar;groovy-4.0.12.jar;httpclient-4.5.13.jar;httpmime-4.5.14.jar;commons-lang3-3.12.0.jar;json-path-5.3.0.jar;xml-path-5.3.0.jar" Hello
# Greetings, Java
# Exception in thread "main" java.lang.NoClassDefFoundError: io/restassured/common/mapper/factory/ObjectMapperFactory

curl https://repo1.maven.org/maven2/io/rest-assured/rest-assured-common/5.3.0/rest-assured-common-5.3.0.jar --output rest-assured-common-5.3.0.jar
java -classpath ".;rest-assured-5.3.0.jar;hamcrest-2.2.jar;groovy-4.0.12.jar;httpclient-4.5.13.jar;httpmime-4.5.14.jar;commons-lang3-3.12.0.jar;json-path-5.3.0.jar;xml-path-5.3.0.jar;rest-assured-common-5.3.0.jar" Hello
# Greetings, Java
# Exception in thread "main" java.lang.NoClassDefFoundError: org/apache/commons/logging/LogFactory

curl https://repo1.maven.org/maven2/commons-logging/commons-logging/1.2/commons-logging-1.2.jar --output commons-logging-1.2.jar
java -classpath ".;rest-assured-5.3.0.jar;hamcrest-2.2.jar;groovy-4.0.12.jar;httpclient-4.5.13.jar;httpmime-4.5.14.jar;commons-lang3-3.12.0.jar;json-path-5.3.0.jar;xml-path-5.3.0.jar;rest-assured-common-5.3.0.jar;commons-logging-1.2.jar" Hello
# Greetings, Java
# Exception in thread "main" groovy.lang.MissingMethodException

# this time it's different, a `groovy.lang.MissingMethodException`
# but using the same strategy above (googling with the keyword `mvn`), I arrived here https://stackoverflow.com/a/63879058/8812880
# then
curl https://repo1.maven.org/maven2/org/apache/httpcomponents/httpcore/4.4.13/httpcore-4.4.13.jar --output httpcore-4.4.13.jar
java -classpath ".;rest-assured-5.3.0.jar;hamcrest-2.2.jar;groovy-4.0.12.jar;httpclient-4.5.13.jar;httpmime-4.5.14.jar;commons-lang3-3.12.0.jar;json-path-5.3.0.jar;xml-path-5.3.0.jar;rest-assured-common-5.3.0.jar;commons-logging-1.2.jar;httpcore-4.4.13.jar" Hello
# Greetings, Java
# Exception in thread "main" java.net.ConnectException: Connection refused: connect

# oh this is it! this time I just need to run the server from #2.5, but rerunning the command above again....
java -classpath ".;rest-assured-5.3.0.jar;hamcrest-2.2.jar;groovy-4.0.12.jar;httpclient-4.5.13.jar;httpmime-4.5.14.jar;commons-lang3-3.12.0.jar;json-path-5.3.0.jar;xml-path-5.3.0.jar;rest-assured-common-5.3.0.jar;commons-logging-1.2.jar;httpcore-4.4.13.jar" Hello
# Greetings, Java
# Exception in thread "main" groovy.lang.MissingMethodException: No signature of method: io.restassured.internal.ContentParser.parse# () is applicable for argument types: (io.restassured.internal.RestAssuredResponseImpl, io.restassured.internal.ResponseParserRegistrar...) values: [io.restassured.internal.RestAssuredResponseImpl@748fe51d, io.restassured.internal.ResponseParserRegistrar@415156bf, ...]
# Possible solutions: wait(), any(), grep()

# hmm 
# at this point, we realized that the `Compile Dependencies (20)` section is crucial 
# and all the non `optional` row from that table is needed here
# plus some of these dependencies also need another dependency
# a tree of dependency, which I find a helper online here http://101coder.com/genTreeUI
# with input from `Maven` tab in https://mvnrepository.com/artifact/io.rest-assured/rest-assured/5.3.0
<!-- https://mvnrepository.com/artifact/io.rest-assured/rest-assured -->
<dependency>
    <groupId>io.rest-assured</groupId>
    <artifactId>rest-assured</artifactId>
    <version>5.3.0</version>
    <scope>test</scope>
</dependency>
# and I got
\- io.rest-assured:rest-assured:jar:5.3.0:test
   +- org.apache.groovy:groovy:jar:4.0.6:test
   +- org.apache.groovy:groovy-xml:jar:4.0.6:test
   +- org.apache.httpcomponents:httpclient:jar:4.5.13:test
   |  +- org.apache.httpcomponents:httpcore:jar:4.4.13:test
   |  +- commons-logging:commons-logging:jar:1.2:test
   |  \- commons-codec:commons-codec:jar:1.11:test
   +- org.apache.httpcomponents:httpmime:jar:4.5.13:test
   +- org.hamcrest:hamcrest:jar:2.1:test
   +- org.ccil.cowan.tagsoup:tagsoup:jar:1.2.1:test
   +- io.rest-assured:json-path:jar:5.3.0:test
   |  +- org.apache.groovy:groovy-json:jar:4.0.6:test
   |  \- io.rest-assured:rest-assured-common:jar:5.3.0:test
   \- io.rest-assured:xml-path:jar:5.3.0:test
      \- org.apache.commons:commons-lang3:jar:3.11:test

# so for the rest of the dependencies...

curl https://repo1.maven.org/maven2/org/apache/groovy/groovy-xml/4.0.6/groovy-xml-4.0.6.jar --output groovy-xml-4.0.6.jar
java -classpath ".;rest-assured-5.3.0.jar;hamcrest-2.2.jar;groovy-4.0.12.jar;httpclient-4.5.13.jar;httpmime-4.5.14.jar;commons-lang3-3.12.0.jar;json-path-5.3.0.jar;xml-path-5.3.0.jar;rest-assured-common-5.3.0.jar;commons-logging-1.2.jar;httpcore-4.4.13.jar;groovy-xml-4.0.6.jar" Hello
# Greetings, Java
# Exception in thread "main" java.lang.NoClassDefFoundError: groovy/json/JsonLexer

curl https://repo1.maven.org/maven2/org/apache/groovy/groovy-json/4.0.6/groovy-json-4.0.6.jar --output groovy-json-4.0.6.jar
java -classpath ".;rest-assured-5.3.0.jar;hamcrest-2.2.jar;groovy-4.0.12.jar;httpclient-4.5.13.jar;httpmime-4.5.14.jar;commons-lang3-3.12.0.jar;json-path-5.3.0.jar;xml-path-5.3.0.jar;rest-assured-common-5.3.0.jar;commons-logging-1.2.jar;httpcore-4.4.13.jar;groovy-xml-4.0.6.jar;groovy-json-4.0.6.jar" Hello
# Greetings, Java

# No error! Yeay!
# which mean the assertion is all true!
# if we change the number in the assertion part,  e.g
then().
    statusCode(200).
    body("lotto.lottoId", equalTo(100),
            "lotto.winners.winnerId", hasItems(123, 44));
# then rerunning javac and java the command will get us this (also using )
javac -classpath ".;rest-assured-5.3.0.jar;hamcrest-2.2.jar" Hello.java
java -classpath ".;rest-assured-5.3.0.jar;hamcrest-2.2.jar;groovy-4.0.12.jar;httpclient-4.5.13.jar;httpmime-4.5.14.jar;commons-lang3-3.12.0.jar;json-path-5.3.0.jar;xml-path-5.3.0.jar;rest-assured-common-5.3.0.jar;commons-logging-1.2.jar;httpcore-4.4.13.jar;groovy-xml-4.0.6.jar;groovy-json-4.0.6.jar" Hello

# Greetings, Java
# WARNING: An illegal reflective access operation has occurred
# WARNING: Illegal reflective access by org.codehaus.groovy.vmplugin.v9.Java9 (file:/D:/keychera/projects/smallsteps/groovy-4.0.12.jar) to constructor java.lang.AssertionError(java.lang.String)
# WARNING: Please consider reporting this to the maintainers of org.codehaus.groovy.vmplugin.v9.Java9
# WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
# WARNING: All illegal access operations will be denied in a future release
# Exception in thread "main" java.lang.AssertionError: 2 expectations failed.
# JSON path lotto.lottoId doesn't match.
# Expected: <100>
#   Actual: <5>
# 
# JSON path lotto.winners.winnerId doesn't match.
# Expected: (a collection containing <123> and a collection containing <44>)
#   Actual: <[23, 54]>

# the Warning is a bit noisy but we got what we wanted here, the java.lang.AssertionError!
```
Now, what have we learned here?

to be continued...
