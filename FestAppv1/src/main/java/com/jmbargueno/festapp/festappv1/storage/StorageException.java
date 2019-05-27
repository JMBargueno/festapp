package com.jmbargueno.festapp.festappv1.storage;

/**
 * Clase de excepciones de Storage
 * @author jmbargueno
 *
 */

public class StorageException extends RuntimeException {

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
