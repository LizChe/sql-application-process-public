package com.codecool.application_process.dao;

import java.util.List;

import com.codecool.application_process.model.Applicant;

public interface ApplicantDao {

    public int create(Applicant applicant) throws DaoException;

    public int update(Applicant applicant) throws DaoException;

    public int deleteApplicantBy(String email) throws DaoException;

    public List<Applicant> getApplicantBy(String firstName, String lastName) throws DaoException;

    public List<Applicant> getApplicantBy(int applicationCode) throws DaoException;

    public List<Applicant> getApplicantsByFirstName(String firstName) throws DaoException;

    public List<Applicant> getApplicantsByEmail(String email) throws DaoException;
}