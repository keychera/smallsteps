# smallsteps

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

# run lotto.clj, a mock server for our next step
bb -e "(load-string (slurp \"https://gist.githubusercontent.com/keychera/75128c639bd9c21bc7e079cd00d858f6/raw/87f5a0ac3b4b44457b5507c9e368c8ea4afa2e43/lotto.clj\"))"
```
