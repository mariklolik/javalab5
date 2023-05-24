package org.example.suppliers;


import org.example.Fabric;
import org.example.details.Body;


import java.util.concurrent.BlockingQueue;

public class BodySupplier implements Runnable, Supplier {
    private final BlockingQueue<Body> bodyStorage;
    private long delay;

    public BodySupplier(BlockingQueue<Body> bodyStorage, long delay) {
        this.bodyStorage = bodyStorage;
        this.delay = delay;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Body body = new Body();
                Fabric.logger
                        .log("[INFO] 👤Producer# " + Thread.currentThread().getName() + " supplied body #" + body.getID());
                bodyStorage.put(body);

                Thread.sleep(delay);

            } catch (InterruptedException e) {
                Fabric.logger
                        .log("[INFO] Body producer was interrupted");
                break;

            }
        }
    }

    @Override
    public long getDelay() {
        return this.delay;
    }

    @Override
    public void setDelay(long newDelay) {
        this.delay = newDelay;
    }
}
