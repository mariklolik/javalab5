package org.example.suppliers;


import org.example.Configuration;
import org.example.Fabric;
import org.example.details.Body;
import org.example.view.FabricView;


import java.util.concurrent.BlockingQueue;

public class BodySupplier implements Runnable {
    private final BlockingQueue<Body> bodyStorage;

    public BodySupplier(BlockingQueue<Body> bodyStorage) {
        this.bodyStorage = bodyStorage;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Body body = new Body();
                Fabric.logger
                        .log("[INFO] 👤Producer# " + Thread.currentThread().getName() + " supplied body #" + body.getID());
                bodyStorage.put(body);

                Thread.sleep(Configuration.getBodySupplierDelay());
                FabricView.updateBodyArraySize(bodyStorage.size());
            } catch (InterruptedException e) {
                Fabric.logger
                        .log("[INFO] Body producer was interrupted");
                break;

            }
        }
    }

}
