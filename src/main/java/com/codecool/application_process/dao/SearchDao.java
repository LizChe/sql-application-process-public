package com.codecool.application_process.dao;

import java.util.List;

public interface SearchDao<T> {

    public List<T> getMatchingResultFrom(String userInput) throws DaoException;

    public List<T> getMatchingResultFrom(int userInput) throws DaoException;
}