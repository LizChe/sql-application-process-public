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
        switch (userChoice) {
            case 1:
                applicantService.createNewApplicant();
                view.displayPressAnythingInput();
                break;
            case 2:
                applicantService.deleteApplicantByEmail();
                view.displayPressAnythingInput();
                break;
            case 3:
                applicantService.updateApplicantsPhoneNumber();
                view.displayPressAnythingInput();
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
            view.clearScreen();
            view.displayFindApplicantMenu();
            handleFindApplicantInput();
        }
    }

    private void handleFindApplicantInput() {
        int userChoice = view.getIntInput();
        switch (userChoice) {
            case 1:
                applicantService.findApplicantByName();
                view.displayPressAnythingInput();
                break;
            case 2:
                applicantService.findApplicantByEmail();
                view.displayPressAnythingInput();
                break;
            case 3:
                applicantService.findApplicantByApplicationCode();
                view.displayPressAnythingInput();
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