package org.example.suppliers;

import org.example.Fabric;
import org.example.details.Engine;


import java.util.concurrent.BlockingQueue;

public class EngineSupplier implements Runnable, Supplier {
    private final BlockingQueue<Engine> engineStorage;
    private long delay;

    public EngineSupplier(BlockingQueue<Engine> engineStorage, long delay) {
        this.engineStorage = engineStorage;
        this.delay = delay;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Engine engine = new Engine();
                Fabric.logger
                        .log("[INFO] 🔥Producer# " + Thread.currentThread().getName() + " supplied engine #" + engine.getID());
                engineStorage.put(engine);

                Thread.sleep(delay);

            } catch (InterruptedException e) {
                Fabric.logger
                        .log("[INFO] Engine producer was interrupted");
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
