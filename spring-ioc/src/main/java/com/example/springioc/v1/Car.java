package com.example.springioc.v1;

public class Car {
    private Framework framework;

    public Car(int size) {
        framework = new Framework(size);
        System.out.println("framework init...");
    }

    public void run() {
        System.out.println("car run...");
    }
}
