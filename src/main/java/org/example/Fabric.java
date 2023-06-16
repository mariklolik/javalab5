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

    public BlockingQueue<Engine> getEngineStorage() {
        return engineStorage;
    }

    public BlockingQueue<Body> getBodyStorage() {
        return bodyStorage;
    }

    public BlockingQueue<Accessories> getAccessoriesStorage() {
        return accessoriesStorage;
    }

    BlockingQueue<Body> bodyStorage;

    BlockingQueue<Accessories> accessoriesStorage;

    public BlockingQueue<Car> getCarsStorage() {
        return carsStorage;
    }

    BlockingQueue<Car> carsStorage;

    Fabric() {
        engineStorage = new ArrayBlockingQueue<>(Configuration.getEngineStorageCapacity());
        bodyStorage = new ArrayBlockingQueue<>(Configuration.getCarBodyStorageCapacity());
        accessoriesStorage = new ArrayBlockingQueue<>(Configuration.getAccessoriesStorageCapacity());
        carsStorage = new ArrayBlockingQueue<>(Configuration.getCarStorageCapacity());

    }


    @Override
    public void run() {
        ExecutorService engineProducer = Executors.newSingleThreadExecutor();
        ExecutorService bodyProducer = Executors.newSingleThreadExecutor();
        ExecutorService accessoriesProducer = Executors.newFixedThreadPool(Configuration.getNumberOfSuppliers());
        ExecutorService carsProducer = Executors.newFixedThreadPool(Configuration.getNumberOfWorkers());

        Runnable engineProducerTask = new EngineSupplier(engineStorage);
        Runnable bodyProducerTask = new BodySupplier(bodyStorage);
        for (int i = 0; i < Configuration.getNumberOfSuppliers(); ++i) {
            Runnable accessoriesProducerTask = new AccessoriesSupplier(accessoriesStorage);
            accessoriesProducer.submit(accessoriesProducerTask);
        }

        for (int i = 0; i < Configuration.getNumberOfWorkers(); ++i) {
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
