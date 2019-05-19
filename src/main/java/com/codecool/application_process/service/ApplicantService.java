package com.codecool.application_process.service;

import java.util.List;

import com.codecool.application_process.dao.ApplicantDao;
import com.codecool.application_process.dao.ApplicantDaoImpl;
import com.codecool.application_process.dao.DaoException;

import com.codecool.application_process.model.Applicant;

import com.codecool.application_process.view.View;

public class ApplicantService {

    private View view;
    private ApplicantDao applicantDao;
    private List<Applicant> applicants;

    public ApplicantService() {
        view = new View();
        applicantDao = new ApplicantDaoImpl();
    }

    public void createNewApplicant() {
        Applicant applicant;
        String firstName;
        String lastName;
        String phoneNumber;
        String email;
        int applicationCode;

        firstName = view.getNotEmptyInputOf("First Name:");
        lastName = view.getNotEmptyInputOf("Last Name:");
        phoneNumber = view.getNotEmptyInputOf("Phone Number:");
        email = view.getNotEmptyInputOf("Email:");
        applicationCode = view.getNotEmptyIntInputOf("Application Code:");

        applicant = new Applicant.Builder()
                .withFirstName(firstName)
                .withLastName(lastName)
                .withPhoneNumber(phoneNumber)
                .withEmail(email)
                .withApplicationCode(applicationCode)
                .build();

        try {
            int rowsAffected = applicantDao.create(applicant);
            view.printText("Successfully Created.\n" + rowsAffected + ": rows have been affected.");
        } catch (DaoException e) {
            view.printText(e.getMessage());
        }
    }

    public void deleteApplicantByEmail() {
        String email = view.getNotEmptyInputOf("Email:");
        try {
            int rowsAffected = applicantDao.deleteApplicantBy(email);
            view.printText("Successfully Deleted.\n" + rowsAffected + ": rows have been affected.");
        } catch (DaoException e) {
            view.printText(e.getMessage());
        }
    }

    public void updateApplicantsPhoneNumber() {
        String phoneNumber;
        Applicant applicant;

        applicants = getApplicantByFullName();

        if (view.isNotEmpty(applicants)) {
            applicant = applicants.get(0);
            phoneNumber = view.getNotEmptyInputOf("New phone number:");
            applicant.setPhoneNumber(phoneNumber);

            try {
                int rowsAffected = applicantDao.update(applicant);
                view.printText("Successfully Updated.\n" + rowsAffected + ": rows have been affected.");
            } catch (DaoException e) {
                view.printText(e.getMessage());
            }
        }
    }

    public void findApplicantByName() {
        String[] fullName;
        String name = view.getNotEmptyInputOf("Name or full name:");

        if (view.isFirstName(name)) {
            try {
                applicants = applicantDao.getApplicantsByFirstName(name);
            } catch (DaoException e) {
                view.printText(e.getMessage());
            }
        } else {
            fullName = name.split(" ", 2);
            try {
                applicants = applicantDao.getApplicantBy(fullName[0], fullName[1]);
            } catch (DaoException e) {
                view.printText(e.getMessage());
            }
        }

        if (view.isNotEmpty(applicants)) {
            view.printApplicantsFullNamePhoneNumber(applicants);
        }
    }

    public void findApplicantByEmail() {
        String email = view.getNotEmptyInputOf("Email:");

        try {
            applicants = applicantDao.getApplicantsByEmail(email);
        } catch (DaoException e) {
            view.printText(e.getMessage());
        }

        if (view.isNotEmpty(applicants)) {
            view.printApplicants(applicants);
        }
    }

    public void findApplicantByApplicationCode() {
        int applicationCode = view.getNotEmptyIntInputOf("Application code:");

        try {
            applicants = applicantDao.getApplicantBy(applicationCode);
        } catch (DaoException e) {
            view.printText(e.getMessage());
        }

        if (view.isNotEmpty(applicants)) {
            view.printApplicants(applicants);
        }
    }

    private List<Applicant> getApplicantByFullName() {
        String[] fullName = view.getValidFullName();

        try {
            applicants = applicantDao.getApplicantBy(fullName[0], fullName[1]);
        } catch (DaoException e) {
            view.printText(e.getMessage());
        }
        return applicants;
    }
}