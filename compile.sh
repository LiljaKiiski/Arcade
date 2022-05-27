#!/bin/sh

echo "starting compilation"; javac *.java

cd gomoku
echo "compiling gomoku"; javac *.java

cd ../finger-dancer
echo "compiling finger-dancer"; javac *.java

cd ../snake
echo "compiling snake"; javac *.java

cd ../element-finder
echo "compiling element-finder"; javac *.java

echo "finished compilation"
