# Ensimag 2A POO - TP 2015/16
# ============================
#
# Ce Makefile permet de compiler le test de l'ihm en ligne de commande.
# Alternative (recommandee?): utiliser un IDE (eclipse, netbeans, ...)
# Le but est d'illustrer les notions de "classpath", a vous de l'adapter
# a votre projet.
#
# Organisation:
#  1) Les sources (*.java) se trouvent dans le repertoire src
#     Les classes d'un package toto sont dans src/toto
#     Les classes du package par defaut sont dans src
#
#  2) Les bytecodes (*.class) se trouvent dans le repertoire bin
#     La hierarchie des sources (par package) est conservee.
#     Pour un package (ici gui.jar), il est aussi dans bin.
#
# Compilation:
#  Options de javac:
#   -d : repertoire dans lequel sont places les .class compiles
#   -classpath : repertoire dans lequel sont cherches les .class deja compiles
#   -sourcepath : repertoire dans lequel sont cherches les .java (dependances)

all: SimBalle SimUnivers 2SimUnivers LifeGame Boid Evolve

Balle:
	javac -d bin/balle/ -classpath bin/gui.jar -sourcepath src/balle/ src/balle/TestBalls.java

exeBalle:
	java -classpath bin/balle:bin/gui.jar  TestBalls

SimBalle:
		javac -d bin/balle/ -classpath bin/gui.jar -sourcepath src/balle/ src/balle/TestBallsSimulator.java

exeSimBalle:
		java -classpath bin/balle:bin/gui.jar  TestBallsSimulator

Univers:
	javac -d bin/immigration/ -classpath bin/gui.jar -sourcepath src/immigration/ src/immigration/TestUnivers.java

exeUnivers:
	java -classpath bin/immigration:bin/gui.jar  TestUnivers

SimUnivers:
	javac -d bin/immigration/ -classpath bin/gui.jar -sourcepath src/immigration/ src/immigration/TestUniversSimulator.java

exeSimUnivers:
	java -classpath bin/immigration:bin/gui.jar  TestUniversSimulator

LifeGame:
	javac -d bin/lifegame/ -classpath bin/gui.jar -sourcepath src/lifegame/ src/lifegame/TestUniversSimulator.java

exeLifeGame:
	java -classpath bin/lifegame:bin/gui.jar  TestUniversSimulator

2Univers:
	javac -d bin/schelling/ -classpath bin/gui.jar -sourcepath src/schelling/ src/schelling/TestUnivers.java

exe2Univers:
	java -classpath bin/schelling:bin/gui.jar  TestUnivers

2SimUnivers:
	javac -d bin/schelling/ -classpath bin/gui.jar -sourcepath src/schelling/ src/schelling/TestUniversSimulator.java

exe2SimUnivers:
	java -classpath bin/schelling:bin/gui.jar  TestUniversSimulator

Boid:
	javac -d bin/boid/ -classpath bin/gui.jar -sourcepath src/boid/ src/boid/TestBoidsSimulator.java

exeBoids:
	java -classpath bin/boid:bin/gui.jar  TestBoidsSimulator

Evolve:
		javac -d bin/evolve/ -classpath bin/gui.jar -sourcepath src/evolve/ src/evolve/TestBoidsSimulator.java

exeEvolve:
		java -classpath bin/evolve:bin/gui.jar  TestBoidsSimulator

testGUI:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestGUI.java


# Execution:
# on peut taper directement la ligne de commande :
#   > java -classpath bin TestGUI
# ou bien lancer l'execution en passant par ce Makefile:
#   > make exeIHM
exeGUI:
	java -classpath bin:bin/gui.jar TestGUI

clean:
	rm -rf bin/*.class
