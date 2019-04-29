package com.codecool.application_process.dao;

class DaoException extends RuntimeException {

    DaoException(String errorMessage) {
        super(errorMessage);
    }
}