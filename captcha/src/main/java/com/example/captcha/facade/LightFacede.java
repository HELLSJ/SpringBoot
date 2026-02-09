package com.example.captcha.facade;

public class LightFacede {
    private Light bedRoomLight = new BedRoomLight();
    private Light hallLight = new HallLight();


    public void lightOn(){
        bedRoomLight.on();
        hallLight.on();
    }

    public void lightOff(){
        bedRoomLight.off();
        hallLight.off();
    }
}
