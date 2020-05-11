tree:
	mvn clean; tree --dirsfirst

execute:
	mvn package; clear; java -jar target/humbug-2.0.0.jar