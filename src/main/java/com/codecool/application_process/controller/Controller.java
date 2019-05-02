package com.codecool.application_process.controller;

import com.codecool.application_process.service.SearchService;

import com.codecool.application_process.view.View;

public class Controller {

    private View view;
    private MentorController mentorController;
    private ApplicantController applicantController;
    private SearchService searchService;
    private boolean isRunning;

    public Controller() {
        view = new View();
        mentorController = new MentorController();
        applicantController = new ApplicantController();
        searchService = new SearchService();
        isRunning = true;
    }

    public void run() {
        isRunning = true;
        view.clearScreen();
        while (isRunning) {
            view.displayLogo();
            view.displayMenu();
           handleController();
        }
    }

    private void handleController() {
        int userChoice = view.getIntInput();
        //view.clearScreen();
        switch (userChoice) {
            case 1:
                mentorController.handleMentorController();
                break;
            case 2:
                applicantController.handleApplicantController();
                break;
            case 3:
                searchService.getSearchResult();
                break;
            case 0:
                isRunning = false;
                break;
            default:
                view.printText("Invalid input.");
                break;
        }
    }
}