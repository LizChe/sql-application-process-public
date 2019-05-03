package com.codecool.application_process.service;

import java.util.List;

import com.codecool.application_process.dao.ApplicantDaoImpl;
import com.codecool.application_process.dao.DaoException;

import com.codecool.application_process.model.Applicant;

import com.codecool.application_process.view.View;

public class ApplicantService {

    private View view;
    private ApplicantDaoImpl applicantDao;
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

        view.printText("First Name:");
        firstName = view.getStringInput();
        view.printText("Last Name:");
        lastName = view.getStringInput();
        view.printText("Phone Number:");
        phoneNumber = view.getStringInput();
        view.printText("Email:");
        email = view.getStringInput();
        view.printText("Application Code:");
        applicationCode = view.getIntInput();

        applicant = new Applicant.Builder()
                .withFirstName(firstName)
                .withLastName(lastName)
                .withPhoneNumber(phoneNumber)
                .withEmail(email)
                .withApplicationCode(applicationCode)
                .build();

        try {
            applicantDao.create(applicant);
            view.printText("Successfully Created.");
        } catch (DaoException e) {
            view.printText(e.getMessage());
        }
    }

    public void deleteApplicantByEmail() {
        String email;
        view.printText("Email:");
        email = view.getStringInput();

        while (email.isEmpty() || email.equals(" ")) {
            view.printText("Input cannot be empty. Try again.");
            email = view.getStringInput();
        }

        try {
            applicantDao.deleteApplicantBy(email);
            view.printText("Successfully Deleted.");
        } catch (DaoException e) {
            view.printText(e.getMessage());
        }
    }

    public void updateApplicantsPhoneNumber() {
        String firstName;
        String lastName;
        String phoneNumber;
        String[] fullName;
        Applicant applicant;

        view.printText("Name & Surname:");
        fullName = view.getStringInput().split(" ");
        while (fullName.length != 2) {
            view.printText("Wrong Input. Try again");
            fullName = view.getStringInput().split(" ");
        }

        firstName = fullName[0];
        lastName = fullName[1];

        try {
            applicants = applicantDao.getApplicantBy(firstName, lastName);
        } catch (DaoException e) {
            view.printText(e.getMessage());
        }

        applicant = applicants.get(0);

        view.printText("New phone number:");
        phoneNumber = view.getStringInput();
        applicant.setPhoneNumber(phoneNumber);

        try {
            applicantDao.update(applicant);
            view.printText("Successfully Updated.");
        } catch (DaoException e) {
            view.printText(e.getMessage());
        }
    }

    public void findApplicantByName() {
        String firstName;
        String lastName;
        String[] fullName;

        view.printText("Name or full name:");
        fullName = view.getStringInput().split(" ");
        if (fullName.length == 1) {
            try {
                applicants = applicantDao.getApplicantsByFirstName(fullName[0]);
            } catch (DaoException e) {
                view.printText(e.getMessage());
            }
        } else if (fullName.length == 2) {
            firstName = fullName[0];
            lastName = fullName[1];
            try {
                applicants = applicantDao.getApplicantBy(firstName, lastName);
            } catch (DaoException e) {
                view.printText(e.getMessage());
            }
        }

        if (verifyIfNotEmpty(applicants)) {
            for (Applicant applicant : applicants) {
                view.printFormattedText("%n%s %s: %s",
                        applicant.getFirstName(), applicant.getLastName(), applicant.getPhoneNumber());
            }
        }
    }

    public void findApplicantByEmail() {
        String email;

        view.printText("Email:");
        email = view.getStringInput();

        try {
            applicants = applicantDao.getApplicantsByEmail(email);
        } catch (DaoException e) {
            view.printText(e.getMessage());
        }

        if (verifyIfNotEmpty(applicants)) {
            printAllData(applicants);
        }
    }

    public void findApplicantByApplicationCode() {
        int applicationCode;

        view.printText("Application code:");
        applicationCode = view.getIntInput();
        try {
            applicants = applicantDao.getApplicantBy(applicationCode);
        } catch (DaoException e) {
            view.printText(e.getMessage());
        }
        if (verifyIfNotEmpty(applicants)) {
            printAllData(applicants);
        }
    }

    private void printAllData(List<Applicant> applicants) {
        for (Applicant applicant : applicants) {
            view.printFormattedText("%n%s %s %s %s %s %s",
                    applicant.getID(), applicant.getFirstName(), applicant.getLastName(),
                    applicant.getPhoneNumber(), applicant.getEmail(), applicant.getApplicationCode());
        }
    }

    private boolean verifyIfNotEmpty(List<Applicant> applicants) {
        if (applicants.isEmpty()) {
            view.printText("No results.");
            return false;
        }
        return true;
    }
}