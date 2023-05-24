package org.example.consumers;

import org.example.Fabric;
import org.example.details.Accessories;
import org.example.details.Body;
import org.example.details.Car;
import org.example.details.Engine;


import java.util.concurrent.BlockingQueue;

public class CarBuilder implements Runnable {
    private final BlockingQueue<Car> carStorage;
    private final BlockingQueue<Engine> enginesStorage;

    private final BlockingQueue<Body> bodiesStorage;
    private final BlockingQueue<Accessories> accessoriesStorage;

    public CarBuilder(BlockingQueue<Engine> engines, BlockingQueue<Body> bodies, BlockingQueue<Accessories> accessories, BlockingQueue<Car> cars) {
        this.enginesStorage = engines;
        this.bodiesStorage = bodies;
        this.accessoriesStorage = accessories;
        this.carStorage = cars;
    }

    @Override
    public void run() {

        while (true) {
            Engine engine = null;
            Body body = null;
            Accessories accessories = null;

            try {
                engine = enginesStorage.take();

                Fabric.logger.
                        log("[INFO] CarBuilder# " + Thread.currentThread().getName() + " taken ENGINE #" + engine.getID());
            } catch (InterruptedException ex) {
                Fabric.logger.
                        log("[ERROR] CarBuilder# " + Thread.currentThread().getName() + " was interrupted");

            }

            try {
                body = bodiesStorage.take();

                Fabric.logger.
                        log("[INFO] CarBuilder# " + Thread.currentThread().getName() + " taken BODY #" + body.getID());
            } catch (InterruptedException ex) {
                Fabric.logger.
                        log("[ERROR] CarBuilder# " + Thread.currentThread().getName() + " was interrupted");

            }

            try {
                accessories = accessoriesStorage.take();

                Fabric.logger.
                        log("[INFO] CarBuilder# " + Thread.currentThread().getName() + " taken ACCESSORIES #" + accessories.getID());
            } catch (InterruptedException ex) {
                Fabric.logger.
                        log("[ERROR] CarBuilder# " + Thread.currentThread().getName() + " was interrupted");

            }


            try {
                Car car = new Car(engine, body, accessories);
                Fabric.logger
                        .log("[INFO] 🚗CarBuilder# " + Thread.currentThread().getName() + " made a car #" + car.getID());
                carStorage.put(car);


            } catch (InterruptedException e) {
                Fabric.logger.
                        log("[ERROR] CarBuilder# " + Thread.currentThread().getName() + " was interrupted");

            }

        }

    }
}
