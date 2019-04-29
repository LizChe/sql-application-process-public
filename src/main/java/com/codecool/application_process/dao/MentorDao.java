package com.codecool.application_process.dao;

import java.util.List;

import com.codecool.application_process.model.Mentor;

public interface MentorDao {

    public List<Mentor> getMentors() throws DaoException;

    public List<Mentor> getMentorsFrom(String city) throws DaoException;
}