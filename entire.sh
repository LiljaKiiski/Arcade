#!/bin/sh

javac Main.java GameButton.java

cd FingerDancer
javac Main.java Arrow.java  Hand.java SoundPlayer.java

cd ..

cd Snake
javac Main.java Snake.java Coordinate.java
