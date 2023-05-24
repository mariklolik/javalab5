package org.example.details;

import org.example.utils.IdGenerator;

public class Car extends CarPart{
    public Engine getEngine() {
        return engine;
    }

    public Body getBody() {
        return body;
    }

    public Accessories getAccessories() {
        return accessories;
    }

    private final Engine engine;
    private final Body body;
    private final Accessories accessories;
    public Car(Engine e, Body b, Accessories a) {
        super(IdGenerator.getNextCarId());
        engine = e;
        body = b;
        accessories = a;
    }


}
