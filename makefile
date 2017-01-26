JAROPT=cmf

blast/Blast.class: blast/Blast.java
	javac blast/Blast.java

all: blast/Blast.class
	
jar: blast/Blast.class 
	jar -$(JAROPT) MANIFEST.MF blast.jar blast/*.class

clean:
	rm blast.jar;
	rm blast/*.class;