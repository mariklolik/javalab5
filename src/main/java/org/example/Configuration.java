package org.example;

import org.example.utils.Utils;

public class Configuration {
    private static final int engineStorageCapacity = Utils.readSetting("EngineStorageCapacity", 50, Integer::parseInt);
    private static final int carBodyStorageCapacity = Utils.readSetting("CarBodyStorageCapacity", 10, Integer::parseInt);
    private static final int accessoriesStorageCapacity = Utils.readSetting("AccessoriesStorageCapacity", 70, Integer::parseInt);
    private static int carStorageCapacity = Utils.readSetting("CarStorageCapacity", 50, Integer::parseInt);
    private static final int numberOfWorkers = Utils.readSetting("NumberOfWorkers", 2, Integer::parseInt);
    private static final int numberOfDealers = Utils.readSetting("NumberOfDealers", 1, Integer::parseInt);
    private static final int numberOfSuppliers = Utils.readSetting("NumberOfSuppliers", 2, Integer::parseInt);
    private static long accessoriesSupplierDelay = Utils.readSetting("AccessoriesSupplierDelay", 3000L, Long::parseUnsignedLong);
    private static long bodySupplierDelay = Utils.readSetting("BodySupplierDelay", 3000L, Long::parseUnsignedLong);
    private static long engineSupplierDelay = Utils.readSetting("EngineSupplierDelay", 3000L, Long::parseUnsignedLong);
    private static long dealerDelay = Utils.readSetting("DealerDelay", 1000L, Long::parseUnsignedLong);

    public static int getEngineStorageCapacity() {
        return engineStorageCapacity;
    }


    public static int getCarBodyStorageCapacity() {
        return carBodyStorageCapacity;
    }


    public static int getAccessoriesStorageCapacity() {
        return accessoriesStorageCapacity;
    }


    public static int getCarStorageCapacity() {
        return carStorageCapacity;
    }

    public static void setCarStorageCapacity(int carStorageCapacity) {
        Configuration.carStorageCapacity = carStorageCapacity;
    }

    public static int getNumberOfWorkers() {
        return numberOfWorkers;
    }


    public static int getNumberOfDealers() {
        return numberOfDealers;
    }


    public static int getNumberOfSuppliers() {
        return numberOfSuppliers;
    }


    public static long getAccessoriesSupplierDelay() {
        return accessoriesSupplierDelay;
    }

    public static void setAccessoriesSupplierDelay(long accessoriesSupplierDelay) {
        Configuration.accessoriesSupplierDelay = accessoriesSupplierDelay;
    }

    public static long getBodySupplierDelay() {
        return bodySupplierDelay;
    }

    public static void setBodySupplierDelay(long bodySupplierDelay) {
        Configuration.bodySupplierDelay = bodySupplierDelay;
    }

    public static long getEngineSupplierDelay() {
        return engineSupplierDelay;
    }

    public static void setEngineSupplierDelay(long engineSupplierDelay) {
        Configuration.engineSupplierDelay = engineSupplierDelay;
    }

    public static long getDealerDelay() {
        return dealerDelay;
    }

    public static void setDealerDelay(long dealerDelay) {
        Configuration.dealerDelay = dealerDelay;
    }




    public static String logsPath = Utils.readSetting("LogsPath", "logs.txt", String::valueOf);
    public static boolean saveLogs = Utils.readSetting("SaveLogs", false, Boolean::parseBoolean);
}

