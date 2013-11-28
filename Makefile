# Laura Richards

JAVA=java
JAVAC=javac


all: GA.class

GA.class: GA.java Entity.java

%.class: %.java
	$(JAVAC) $^


clean:
	rm -f *.class *~
