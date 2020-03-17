# ESI - DEV2 PROJET - HUMBUG
![GitHub top language](https://img.shields.io/github/languages/top/asassoye/ESI-dev2-project?style=for-the-badge)
![GitHub](https://img.shields.io/github/license/asassoye/ESI-dev2-project?style=for-the-badge)
![GitHub last commit](https://img.shields.io/github/last-commit/asassoye/ESI-dev2-project?style=for-the-badge)
![GitHub Workflow Status](https://img.shields.io/github/workflow/status/asassoye/ESI-dev2-project/Java%20CI?style=for-the-badge)

## Structure du projet
```
.
├── src
│   ├── main
│   │   └── java
│   │       └── g54327
│   │           └── humbug
│   │               ├── model
│   │               │   ├── Board.java
│   │               │   ├── Direction.java
│   │               │   ├── Position.java
│   │               │   ├── Square.java
│   │               │   └── SquareType.java
│   │               └── Main.java
│   └── test
│       └── java
│           ├── g54327
│           │   └── humbug
│           │       └── MainTest.java
│           └── pbt
│               └── humbug
│                   └── model
│                       └── PositionTest.java
├── statement
│   ├── consignes-projet.pdf
│   ├── enonce-1.pdf
│   └── enonce-2.pdf
├── DEV2-PROJECT.iml
├── LICENSE
├── Makefile
├── pom.xml
└── README.md

```

## Compiler
### Maven
Pour compiler le projet, executer :
```shell script
mvn build
```

## Package
### Maven
Pour compiler le projet, executer :
```shell script
mvn package
```

Puis executez:
```shell script
java -jar target/humbug-0.1.0.jar
```

## Tests
### Maven
Pour executer les testes, executer la commande:
```shell script
mvn test 
```
