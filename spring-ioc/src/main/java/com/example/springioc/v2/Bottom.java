package com.example.springioc.v2;

public class Bottom {
    private Tire tire;

    public Bottom(Tire tire) {
        this.tire = tire;
        System.out.println("tire init...");
    }
}
