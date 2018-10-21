#!/bin/bash
mkdir -p bin
javac -d bin -sourcepath src src/com/example/orderscheduler/OrderScheduler.java
java -cp bin com.example.orderscheduler.OrderScheduler

