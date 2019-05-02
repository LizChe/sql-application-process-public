package com.codecool.application_process.controller;

import com.codecool.application_process.service.ApplicantService;
import com.codecool.application_process.view.View;

class ApplicantController {

    private ApplicantService applicantService;
    private View view;
    private boolean isRunning;
    private boolean isFindingApplicant;

    ApplicantController() {
        applicantService = new ApplicantService();
        view = new View();
        isRunning = true;
        isFindingApplicant = true;
    }

    void handleApplicantController() {
        isRunning = true;
        while (isRunning) {
            view.clearScreen();
            view.displayApplicantsMenu();
            handleUserInput();
        }
    }

    private void handleUserInput() {
        int userChoice = view.getIntInput();
        //view.clearScreen();
        switch (userChoice) {
            case 1:
                applicantService.createNewApplicant();
                break;
            case 2:
                applicantService.deleteApplicantByEmail();
                break;
            case 3:
                applicantService.updateApplicantsPhoneNumber();
                break;
            case 4:
                handleFindApplicantController();
                break;
            case 0:
                isRunning = false;
                break;
            default:
                view.printText("Invalid Input.");
                break;
        }
    }

    private void handleFindApplicantController() {
        isFindingApplicant = true;
        while (isFindingApplicant) {
            //view.clearScreen();
            view.displayFindApplicantMenu();
            handleFindApplicantInput();
        }
    }

    private void handleFindApplicantInput() {
        int userChoice = view.getIntInput();
        //view.clearScreen();
        switch (userChoice) {
            case 1:
                applicantService.findApplicantByName();
                break;
            case 2:
                applicantService.findApplicantByEmail();
                break;
            case 3:
                applicantService.findApplicantByApplicationCode();
                break;
            case 0:
                isFindingApplicant = false;
                break;
            default:
                view.printText("Invalid Input.");
                break;
        }
    }
}