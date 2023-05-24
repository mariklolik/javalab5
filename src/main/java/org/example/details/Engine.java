package org.example.details;

import org.example.utils.IdGenerator;

public class Engine extends CarPart {
    public Engine(){
        super(IdGenerator.getNextEngineId());
    }
}
