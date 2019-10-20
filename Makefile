
build:
	mvn clean;
	mvn install

release:
	mvn release:clean release:prepare
	mvn release:perform
