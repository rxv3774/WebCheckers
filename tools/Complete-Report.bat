mvn exec:exec@tests-and-coverage && mvn clean test-compile surefire:test@appl jacoco:report@appl && mvn clean test-compile surefire:test@model jacoco:report@model && mvn clean test-compile surefire:test@ui jacoco:report@ui 

PAUSE