#!/bin/sh

javac Main.java GameButton.java

cd FingerDancer
javac Main.java Arrow.java  Hand.java SoundPlayer.java

cd ..

cd Snake
javac Main.java Snake.java Coordinate.java

cd ..

cd ElementFinder
javac Background.java Character.java Element.java Leaderboard.java Level.java Main.java Modules.java MyClock.java MyPanel.java Object.java Obstacle.java SoundPlayer.java
