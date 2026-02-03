package com.example.springioc.v2;

public class Car {
    private Framework framework;

    public Car(Framework framework) {
        this.framework = framework;
        System.out.println("framework init...");
    }

    public void run() {
        System.out.println("car run...");
    }
}

