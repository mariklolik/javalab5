package org.example;

import org.example.utils.Utils;

public class Configuration {
    public static final int engineStorageCapacity = Utils.readSetting("EngineStorageCapacity", 50, Integer::parseInt);
    public static final int carBodyStorageCapacity = Utils.readSetting("CarBodyStorageCapacity", 10, Integer::parseInt);
    public static final int accessoriesStorageCapacity = Utils.readSetting("AccessoriesStorageCapacity", 70, Integer::parseInt);
    public static final int carStorageCapacity = Utils.readSetting("CarStorageCapacity", 50, Integer::parseInt);
    public static final int numberOfWorkers = Utils.readSetting("NumberOfWorkers", 2, Integer::parseInt);
    public static final int numberOfDealers = Utils.readSetting("NumberOfDealers", 1, Integer::parseInt);
    public static final int numberOfSuppliers = Utils.readSetting("NumberOfSuppliers", 2, Integer::parseInt);
    public static final long accessoriesSupplierDelay = Utils.readSetting("AccessoriesSupplierDelay", 3000L, Long::parseUnsignedLong);
    public static final long bodySupplierDelay = Utils.readSetting("BodySupplierDelay", 3000L, Long::parseUnsignedLong);
    public static final long engineSupplierDelay = Utils.readSetting("EngineSupplierDelay", 3000L, Long::parseUnsignedLong);
    public static final long dealerDelay = Utils.readSetting("DealerDelay", 1000L, Long::parseUnsignedLong);
    public static final String logsPath = Utils.readSetting("LogsPath", "logs.txt", String::valueOf);
    public static final boolean saveLogs = Utils.readSetting("SaveLogs", false, Boolean::parseBoolean);
}

