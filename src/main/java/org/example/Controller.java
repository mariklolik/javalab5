package org.example;

import org.example.consumers.Dealer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Controller {
    private final static ExecutorService fabricPool  = Executors.newSingleThreadExecutor();
    private final static ExecutorService dealerPool= Executors.newFixedThreadPool(Configuration.numberOfDealers);


    static void control(Fabric fabric) {
        fabricPool.submit(fabric);
        for (int i = 0; i < Configuration.numberOfDealers; ++i) {
            Runnable dealer = new Dealer(fabric.getCarsStorage());
            dealerPool.submit(dealer);
        }
        fabricPool.shutdown();
        dealerPool.shutdown();
    }
}
