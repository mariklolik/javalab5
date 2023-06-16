package org.example.suppliers;

import org.example.Configuration;
import org.example.Fabric;
import org.example.details.Accessories;
import org.example.view.FabricView;


import java.util.concurrent.BlockingQueue;

public class AccessoriesSupplier implements Runnable {
    private final BlockingQueue<Accessories> accessoriesStorage;

    public AccessoriesSupplier(BlockingQueue<Accessories> bodyStorage) {
        this.accessoriesStorage = bodyStorage;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Accessories acc = new Accessories();
                Fabric.logger
                        .log("[INFO] 🕶️Producer# " + Thread.currentThread().getName() + " supplied accessories #" + acc.getID());
                accessoriesStorage.put(acc);

                Thread.sleep(Configuration.getAccessoriesSupplierDelay());
                FabricView.updateAccessoriesArraySize(accessoriesStorage.size());
            } catch (InterruptedException e) {
                Fabric.logger
                        .log("[INFO] Accessories producer was interrupted");
                break;

            }
        }
    }

}
