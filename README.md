# smallsteps

1. Hello Java!

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

2. My friend can HelpMe
```sh
# new file HelpMe.java, Hello uses HelpMe

javac Hello.java HelpMe.java
java Hello
# => Greetings, Java
```
