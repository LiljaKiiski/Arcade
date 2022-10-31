#!/bin/sh

echo "starting compilation"; javac *.java

cd gomoku
echo "compiling gomoku"; javac -d bin *.java

cd ../finger-dancer
echo "compiling finger-dancer"; javac -d bin *.java

cd ../snake
echo "compiling snake"; javac -d bin *.java

cd ../element-finder
echo "compiling element-finder"; javac -d bin *.java

echo "finished compilation"
