package org.example.consumers;

import org.example.Configuration;
import org.example.Fabric;
import org.example.details.Car;

import java.util.concurrent.BlockingQueue;

public class Dealer implements Runnable {
    private final BlockingQueue<Car> carStorage;

    public Dealer(BlockingQueue<Car> cars) {
        this.carStorage = cars;

    }

    @Override
    public void run() {
        while (true) {
            Car car;
            try {
                car = carStorage.take();

                Fabric.logger.

                        log(": 🤝Dealer %s: 🚗Auto:%d (Body: %d, Motor: %d, Accessory:%d)".formatted(
                                Thread.currentThread().getName(),
                                car.getID(),
                                car.getBody().getID(),
                                car.getEngine().getID(),
                                car.getAccessories().getID()

                        ));
                Thread.sleep(Configuration.dealerDelay);
            } catch (InterruptedException ex) {
                Fabric.logger.
                        log("[ERROR] CarBuilder# " + Thread.currentThread().getName() + " was interrupted");

            }
        }
    }
}
