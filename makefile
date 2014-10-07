all:
	javac -cp src src/chess/Application.java

run:
	java -cp ./src/ chess.Application

clean:
	find . -name "*.class" -type f -delete