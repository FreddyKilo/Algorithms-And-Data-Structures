package com.freddykilo.AlgorithmsAndDataStructures;

/**
 * Created by fred.kudelka on 11/25/15.
 */
public class Timer {

    public long startTime = 0;
    public long stopTime = 0;
    public long totalMillis = 0;
    public String description;
    private static final Timer INSTANCE = new Timer();

    private Timer(){

    }

    public static Timer getInstance(){
        if(INSTANCE == null){
            return new Timer();
        }
        return INSTANCE;
    }

    public void start(){
        startTime = System.currentTimeMillis();
    }

    public void stop(){
        stopTime = System.currentTimeMillis();
        totalMillis = getResult();
    }

    public void clear(){
        startTime = 0;
        stopTime = 0;
    }

    private long getResult(){
        return stopTime - startTime;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void printResult(){
        if(totalMillis > 0) {
            System.out.println(description + ": " + totalMillis + " millis");
        } else if(startTime > 0){
            System.out.println("Timer has not yet ended.");
        } else {
            System.out.println("Timer has not yet started.");
        }
    }
}
