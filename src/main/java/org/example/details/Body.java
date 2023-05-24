package org.example.details;

import org.example.utils.IdGenerator;

public class Body extends CarPart {
    public Body() {
        super(IdGenerator.getNextBodyId());
    }
}

