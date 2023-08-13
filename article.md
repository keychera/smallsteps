# Rest-assured with just Java (NOT A TUTORIAL)

If you have a need to automate testing a Rest API of a given software product, you can rest assured that Rest-assured is one option you can use.

It is an industry standard library and there are a lot of tutorial that can help you to understand how to use them.

But, as the title said, this article is not a tutorial to teach you anything at all, it's an article where I want to show you what I have learned about Java.

Those things about Java that, in my case, something that is familiar and ever present since I learn the language for the first time, but I haven't had a full understanding of what they actually are. Up until now.

So that's why I want to run Rest-assured with just Java so I can test my understanding, discover the reasons of why some Chesterton's Fence is there, and show it to you in this article

emphasis on the word "just", because I mean it. No gradle, no maven, no IDE, we will do programming caveman-style with just notepad, terminal, and a pre-installed JDK environment. Plus maybe a browser to search stuff up or ask ChatGPT, or if you readers want to follow along, have this article open while you do it.

and it doesn't have to be Rest-assured actually, it can be any java library, I just choose it because I like the name :) (Cool article title, isn't it?)

# Preparing our wooden club

a group of caveman prepares to hunt a mammoth, but first, they have to check their weaponry first and ensure where the prey is located. I have briefly mentioned what we will need but I will go a little bit into the details here. So our wooden clubs are:

1. Notepad, TextEdit, or any text editor of your choice
2. Terminal
3. a JDK environment

    I am not gonna go over how to install JDK here but your device might luckily be one of the 3 Billion devices that have Java, so you can check in your terminal with

    ```sh
    java -version
    ```
        
    if it shows the version, then you are good to go. If not, then maybe you can visit https://www.oracle.com/id/java/technologies/downloads/#java17
        
    What I'll be using is Java version 17 but I'm sure previous versions are still fine as well or at least make sure you are Java version >8. 

There are some more "software" that we were gonna need, but first I want to have a look at the mammoth that we're gonna hunt. which is located in the http://rest-assured.io homepage accessed on 13 August 2023. The example code of Rest-assured. (I have to be specific on the date so the 12th reader of this article reading this on 2123 might not be confused if the website is changed by then)

![Rest-assured.io homepage](img\rest-assured-homepage.png)

Our goal is to run that Java code.

So moving on to the remaining things we're gonna use, we will need:

4. a software to access the internet

    Which you probably have used already to access that homepage, the Browsers, or `curl` that is usually already pre-installed in your devices. You can use either one but I suggest to have both ready because some of the steps we're gonna take will are faster to be done in the browser and some other are faster with curl

5. a software called Babashka

    