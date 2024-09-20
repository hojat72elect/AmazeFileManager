package com.amaze.filemanager.database.models;

/**
 * Simple StringWrapper.
 */
public class StringWrapper {

    public final String value;

    public StringWrapper(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
