package org.example.details;

import org.example.utils.IdGenerator;

public class Accessories extends CarPart{
    public Accessories() {
        super(IdGenerator.getNextAccessoriesId());
    }
}


