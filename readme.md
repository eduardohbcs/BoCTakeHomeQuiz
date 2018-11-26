General Notes
=============================
KISS was the driving force here when working on this assignment due to limited time available. Yes, I did cut some corners, in particular with prettying up the
UI. I'm sure you understand.

This little project was written in Java 7 as I'm much more comfortable with it than Java 8. 

I ended up using a simple SpringBoot backend with a single rest endpoint. The endpoint loads up the .csv with the weather data and serves up requests from 
the two html pages used to query/view historical weather data.

I'm not familiar with Thymeleaf, and again due to time constraints I decided to keep it very simple and just used HTML+Javascript. Couple of APIs used might
not work with older browsers but there was no requirement around that.

Regarding tests, I couldn't think beyond exercising the API, so I added some basic tests around that. One of the tests is commented out due to SpringBoot
misbehaving regarding exception mapping. I ran out of time searching for a solution for this.

In any case, just import the pom.xml file on your preferred IDE and it should be all ready to go. You can also use Maven to build and test the project 
via (install, test, commands). 

To run it, run maven install to create a runnable jar. Then open a command prompt (in Windows) and browse to the project's target folder. Then run:
java -jar takehomequiz-1.0.0.jar 

Summary of technologies:
- Java SE 7;
- Maven;
- SpringBoot 2.0.5;
- Vanilla HTML stack (Html+CSS+Javascript);  