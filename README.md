# ESI - DEV2 PROJET - HUMBUG
![GitHub top language](https://img.shields.io/github/languages/top/asassoye/ESI-dev2-project?style=for-the-badge)
![GitHub](https://img.shields.io/github/license/asassoye/ESI-dev2-project?style=for-the-badge)
![GitHub last commit](https://img.shields.io/github/last-commit/asassoye/ESI-dev2-project?style=for-the-badge)
![GitHub Workflow Status](https://img.shields.io/github/workflow/status/asassoye/ESI-dev2-project/Java%20CI?style=for-the-badge)

![Humbug](.github/humbug.png)

## Structure du projet
```
.
├── src
│   ├── main
│   │   └── java
│   │       └── g54327
│   │           ├── humbug
│   │           │   ├── controller
│   │           │   │   └── Controller.java
│   │           │   ├── model
│   │           │   │   ├── Animals
│   │           │   │   │   ├── Animal.java
│   │           │   │   │   ├── Snail.java
│   │           │   │   │   └── Spider.java
│   │           │   │   ├── Exceptions
│   │           │   │   │   ├── AnimalDiesException.java
│   │           │   │   │   ├── LevelNotStartedException.java
│   │           │   │   │   ├── NullPositionException.java
│   │           │   │   │   ├── NullSquareException.java
│   │           │   │   │   └── PositionOutOfBoundException.java
│   │           │   │   ├── Squares
│   │           │   │   │   ├── Square.java
│   │           │   │   │   └── SquareType.java
│   │           │   │   ├── Structures
│   │           │   │   │   ├── Board.java
│   │           │   │   │   ├── Direction.java
│   │           │   │   │   └── Position.java
│   │           │   │   ├── Game.java
│   │           │   │   └── Model.java
│   │           │   ├── view
│   │           │   │   └── text
│   │           │   │       ├── InterfaceView.java
│   │           │   │       └── View.java
│   │           │   └── Main.java
│   │           └── utils
│   │               └── RobustScanner.java
│   └── test
│       └── java
│           ├── g54327
│           │   └── humbug
│           │       └── MainTest.java
│           └── pbt
│               └── humbug
│                   └── model
│                       ├── animals
│                       │   ├── SnailTest.java
│                       │   └── SpiderTest.java
│                       ├── BoardTest.java
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
## Documentation
Vérifiez la documentation [ici](https://asassoye.github.io/ESI-dev2-project/index.html)

## Compiler
### Maven
Pour compiler le projet, executer :
```
mvn build
```

## Package
### Maven
Pour compiler le projet, executer :
```
mvn package
```

Puis executez:
```
java -jar target/humbug-1.0.0.jar
```

## Tests
### Maven
Pour executer les testes, executer la commande:
```
mvn test 
```
