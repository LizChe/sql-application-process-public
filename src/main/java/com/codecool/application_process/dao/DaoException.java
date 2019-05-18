package com.codecool.application_process.dao;

public class DaoException extends IllegalArgumentException {

    DaoException(String errorMessage) {
        super(errorMessage);
    }
}