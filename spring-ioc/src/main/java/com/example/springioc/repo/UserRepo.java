package com.example.springioc.repo;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepo {
    public void doRepo(){
        System.out.println("do repository...");
    }
}
