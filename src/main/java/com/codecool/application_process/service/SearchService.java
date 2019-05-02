package com.codecool.application_process.service;

import java.util.List;

import com.codecool.application_process.dao.ApplicantDaoImpl;
import com.codecool.application_process.dao.DaoException;
import com.codecool.application_process.dao.MentorDaoImpl;

import com.codecool.application_process.model.Applicant;
import com.codecool.application_process.model.Mentor;

import com.codecool.application_process.view.View;

public class SearchService {

    private ApplicantDaoImpl applicantsDao;
    private MentorDaoImpl mentorsDao;
    private View view;
    private List<Mentor> mentorsSearchResult;
    private List<Applicant> applicantsSearchResult;

    public SearchService() {
        applicantsDao = new ApplicantDaoImpl();
        mentorsDao = new MentorDaoImpl();
        view = new View();
    }

    public void getSearchResult() {
        view.printText("Search:");
        String search = view.getStringInput();

        if (checkIfInputIsInt(search)) {
            int number = getIntFromSearch(search);
            try {
                mentorsSearchResult = mentorsDao.getMatchingResultFrom(number);
                applicantsSearchResult = applicantsDao.getMatchingResultFrom(number);
            } catch (DaoException e) {
                view.printText(e.getMessage());
            }
        } else {
            try {
                mentorsSearchResult = mentorsDao.getMatchingResultFrom(search);
                applicantsSearchResult = applicantsDao.getMatchingResultFrom(search);
            } catch (DaoException e) {
                view.printText(e.getMessage());
            }
        }

        if (verifyIfNotEmpty(applicantsSearchResult, mentorsSearchResult)) {
            view.printText("\nApplicants:");
            displayApplicantsResult(applicantsSearchResult);
            view.printText("\nMentors:");
            displayMentorsResult(mentorsSearchResult);
        }
    }

    private int getIntFromSearch(String search) {
        return Integer.parseInt(search);
    }

    private boolean checkIfInputIsInt(String search) {
        String regex = "\\d+";
        return search.matches(regex);
    }

    private void displayApplicantsResult(List<Applicant> applicantsSearchResult) {
        for (Applicant applicant : applicantsSearchResult) {
            view.printFormattedText("%n%s %s %s %s %s %s",
                    applicant.getID(), applicant.getFirstName(), applicant.getLastName(),
                    applicant.getPhoneNumber(), applicant.getEmail(), applicant.getApplicationCode());
        }
    }

    private void displayMentorsResult(List<Mentor> mentorsSearchResult) {
        for (Mentor mentor : mentorsSearchResult) {
            view.printFormattedText("%n%s %s %s %s %s %s %s %s",
                    mentor.getID(), mentor.getFirstName(), mentor.getLastName(),
                    mentor.getNickName(), mentor.getPhoneNumber(), mentor.getEmail(),
                    mentor.getCity(), mentor.getFavouriteNumber());
        }
    }

    private boolean verifyIfNotEmpty(List<Applicant> applicants, List<Mentor> mentors) {
        if (applicants.isEmpty() && mentors.isEmpty()) {
            view.printText("No results.");
            return false;
        }
        return true;
    }
}