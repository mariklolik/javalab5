package org.example;

import org.example.consumers.CarBuilder;
import org.example.details.Accessories;
import org.example.details.Body;
import org.example.details.Car;
import org.example.details.Engine;
import org.example.suppliers.AccessoriesSupplier;
import org.example.suppliers.BodySupplier;
import org.example.suppliers.EngineSupplier;
import org.example.utils.Logger;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Fabric implements Runnable {
    public static Logger logger = Logger.getInstance(Configuration.logsPath);


    BlockingQueue<Engine> engineStorage;
    BlockingQueue<Body> bodyStorage;

    BlockingQueue<Accessories> accessoriesStorage;

    public BlockingQueue<Car> getCarsStorage() {
        return carsStorage;
    }

    BlockingQueue<Car> carsStorage;

    Fabric() {
        engineStorage = new ArrayBlockingQueue<>(Configuration.engineStorageCapacity);
        bodyStorage = new ArrayBlockingQueue<>(Configuration.carBodyStorageCapacity);
        accessoriesStorage = new ArrayBlockingQueue<>(Configuration.accessoriesStorageCapacity);
        carsStorage = new ArrayBlockingQueue<>(Configuration.carStorageCapacity);

    }


    @Override
    public void run() {
        ExecutorService engineProducer = Executors.newSingleThreadExecutor();
        ExecutorService bodyProducer = Executors.newSingleThreadExecutor();
        ExecutorService accessoriesProducer = Executors.newFixedThreadPool(Configuration.numberOfSuppliers);
        ExecutorService carsProducer = Executors.newFixedThreadPool(Configuration.numberOfWorkers);

        Runnable engineProducerTask = new EngineSupplier(engineStorage, Configuration.engineSupplierDelay);
        Runnable bodyProducerTask = new BodySupplier(bodyStorage, Configuration.bodySupplierDelay);
        for (int i = 0; i < Configuration.numberOfSuppliers; ++i) {
            Runnable accessoriesProducerTask = new AccessoriesSupplier(accessoriesStorage, Configuration.accessoriesSupplierDelay);
            accessoriesProducer.submit(accessoriesProducerTask);
        }

        for (int i = 0; i < Configuration.numberOfWorkers; ++i) {
            Runnable carsProducerTask = new CarBuilder(engineStorage, bodyStorage, accessoriesStorage, carsStorage);
            carsProducer.submit(carsProducerTask);
        }

        engineProducer.submit(engineProducerTask);
        bodyProducer.submit(bodyProducerTask);


        engineProducer.shutdown();
        bodyProducer.shutdown();
        accessoriesProducer.shutdown();
        carsProducer.shutdown();
    }
}
