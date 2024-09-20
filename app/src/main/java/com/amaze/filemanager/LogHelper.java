package com.amaze.filemanager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class LogHelper {
    private static final Logger LOG = LoggerFactory.getLogger(LogHelper.class);

    private LogHelper() {
    }

    public static void logOnProductionOrCrash(String message) {
        if (BuildConfig.DEBUG) {
            throw new IllegalStateException(message);
        } else {
            LOG.error(message);
        }
    }
}
