package org.example.details;


public abstract class CarPart {
    private final long id;

    public CarPart(long ProductID){
        id = ProductID;
    }

    public long getID(){
        return id;
    }
}