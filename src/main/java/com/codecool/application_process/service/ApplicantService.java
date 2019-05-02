package com.codecool.application_process.service;

import java.util.List;

import com.codecool.application_process.model.Applicant;
import com.codecool.application_process.dao.ApplicantDaoImpl;
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

        applicantDao.create(applicant);
        view.printText("Successfully Created.");
    }

    public void deleteApplicantByEmail() {
        String email;
        view.printText("Email:");
        email = view.getStringInput();

        applicantDao.deleteApplicantBy(email);
        view.printText("Successfully Deleted.");
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

        applicants = applicantDao.getApplicantBy(firstName, lastName);
        applicant = applicants.get(0);

        view.printText("New phone number:");
        phoneNumber = view.getStringInput();
        applicant.setPhoneNumber(phoneNumber);
        applicantDao.update(applicant);
        view.printText("Successfully Updated.");
    }

    public void findApplicantByName() {
        String firstName;
        String lastName;
        String[] fullName;

        view.printText("Name or full name:");
        fullName = view.getStringInput().split(" ");
        if (fullName.length == 1) {
            applicants = applicantDao.getApplicantsByFirstName(fullName[0]);
        } else if (fullName.length == 2) {
            firstName = fullName[0];
            lastName = fullName[1];
            applicants = applicantDao.getApplicantBy(firstName, lastName);
        }

        for (Applicant applicant : applicants) {
            view.printFormattedText("%n%s %s: %s", applicant.getFirstName(), applicant.getLastName(), applicant.getPhoneNumber());
        }
    }

    public void findApplicantByEmail() {
        String email;

        view.printText("Email:");
        email = view.getStringInput();

        applicants = applicantDao.getApplicantsByEmail(email);
        printAllData(applicants);
    }

    public void findApplicantByApplicationCode() {
        int applicationCode;

        view.printText("Application code:");
        applicationCode = view.getIntInput();
        applicants = applicantDao.getApplicantBy(applicationCode);
        printAllData(applicants);
    }

    private void printAllData(List<Applicant> applicants) {
        for (Applicant applicant : applicants) {
            view.printFormattedText("%n%s %s %s %s %s %s", applicant.getID(), applicant.getFirstName(), applicant.getLastName(),
                    applicant.getPhoneNumber(), applicant.getEmail(), applicant.getApplicationCode());
        }
    }
}