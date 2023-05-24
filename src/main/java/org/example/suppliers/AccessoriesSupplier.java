package org.example.suppliers;

import org.example.Fabric;
import org.example.details.Accessories;


import java.util.concurrent.BlockingQueue;

public class AccessoriesSupplier implements Runnable, Supplier {
    private final BlockingQueue<Accessories> accessoriesStorage;
    private long delay;

    public AccessoriesSupplier(BlockingQueue<Accessories> bodyStorage, long delay) {
        this.accessoriesStorage = bodyStorage;
        this.delay = delay;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Accessories acc = new Accessories();
                Fabric.logger
                        .log("[INFO] 🕶️Producer# " + Thread.currentThread().getName() + " supplied accessories #" + acc.getID());
                accessoriesStorage.put(acc);

                Thread.sleep(delay);

            } catch (InterruptedException e) {
                Fabric.logger
                        .log("[INFO] Accessories producer was interrupted");
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
