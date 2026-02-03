package com.example.springioc.v2;

public class Main {
    public static void main(String[] args) {
        Tire tire = new Tire(17);
        Bottom bottom = new Bottom(tire);
        Framework framework = new Framework(bottom);
        Car car = new Car(framework);
        car.run();
    }
}
