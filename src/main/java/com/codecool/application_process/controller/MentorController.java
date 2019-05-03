package com.codecool.application_process.controller;

import com.codecool.application_process.service.MentorService;
import com.codecool.application_process.view.View;

class MentorController {

    private MentorService mentorService;
    private View view;
    private boolean isRunning;

    MentorController() {
        mentorService = new MentorService();
        view = new View();
        isRunning = true;
    }

    void handleMentorController() {
        isRunning = true;
        while (isRunning) {
            view.clearScreen();
            view.displayMentorsMenu();
            handleUserInput();
        }
    }

    private void handleUserInput() {
        int userChoice = view.getIntInput();
        switch (userChoice) {
            case 1:
                mentorService.getMentorsFullName();
                view.displayPressAnythingInput();
                break;
            case 2:
                mentorService.getMentorsByChosenCity();
                view.displayPressAnythingInput();
                break;
            case 0:
                isRunning = false;
                break;
            default:
                view.printText("Invalid Input.");
                break;
        }
    }
}