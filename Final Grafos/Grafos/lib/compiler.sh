#!/bin/bash

javac -d ../bin/ -cp ../src ../src/model/*
javac -d ../bin/ -cp ../src ../src/view/*
javac -d ../bin/ -cp ../src ../src/controller/*
javac -d ../bin/ -cp ../src ../src/Main.java