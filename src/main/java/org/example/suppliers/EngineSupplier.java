package org.example.suppliers;

import org.example.Configuration;
import org.example.Fabric;
import org.example.details.Engine;
import org.example.view.FabricView;


import java.util.concurrent.BlockingQueue;

public class EngineSupplier implements Runnable {
    private final BlockingQueue<Engine> engineStorage;

    public EngineSupplier(BlockingQueue<Engine> engineStorage) {
        this.engineStorage = engineStorage;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Engine engine = new Engine();
                Fabric.logger
                        .log("[INFO] 🔥Producer# " + Thread.currentThread().getName() + " supplied engine #" + engine.getID());
                engineStorage.put(engine);
                FabricView.updateEngineArraySize(engineStorage.size());
                Thread.sleep(Configuration.getEngineSupplierDelay());

            } catch (InterruptedException e) {
                Fabric.logger
                        .log("[INFO] Engine producer was interrupted");
                break;

            }
        }
    }

}
