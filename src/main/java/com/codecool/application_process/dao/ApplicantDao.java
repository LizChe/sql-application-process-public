package com.codecool.application_process.dao;

import java.util.List;

import com.codecool.application_process.model.Applicant;

public interface ApplicantDao {

    public List<Applicant> getPhoneNumberBy(String firstNameOrEmail);
    public List<Applicant> getApplicantBy(int applicationCode);
    public List<Applicant> getApplicantBy(String name, String surname);
    public void addNew(Applicant applicant);
    public void updateApplicant(String phoneNumber, String name, String surname);
    public void deleteApplicantsBy(String email);
}