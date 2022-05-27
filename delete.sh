#!/bin/sh

mkdir temp

mv *.class temp

cd temp; rm -rf __pycache__;
cd ../bounce; mv  __pycache__ ../temp
cd ../temp; rm -rf __pycache__;

cd ../space-game; mv __pycache__ ../temp

cd ../element-finder; mv *.class ../temp

cd ../snake;  mv *.class ../temp

cd ../finger-dancer; mv *.class ../temp

cd ../gomoku; mv *.class ../temp
