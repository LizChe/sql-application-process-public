package com.codecool.application_process.dao;

public class DaoException extends RuntimeException {

    public DaoException(String errorMessage) {
        super(errorMessage);
    }
}