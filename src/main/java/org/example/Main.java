package org.example;


import org.example.view.FabricView;

public class Main {

    public static void main(String[] args) {
        Fabric fabric = new Fabric();
        Controller.control(fabric);
        new FabricView(fabric);
    }
}
