package com.codecool.application_process.service;

import java.util.List;

import com.codecool.application_process.dao.ApplicantDaoImpl;
import com.codecool.application_process.dao.DaoException;
import com.codecool.application_process.dao.MentorDaoImpl;

import com.codecool.application_process.dao.SearchDao;
import com.codecool.application_process.model.Applicant;
import com.codecool.application_process.model.Mentor;

import com.codecool.application_process.view.View;

public class SearchService {

    private SearchDao<Applicant> searchApplicantDao;
    private SearchDao<Mentor> searchMentorDao;
    private View view;
    private List<Mentor> mentorsSearchResult;
    private List<Applicant> applicantsSearchResult;

    public SearchService() {
        searchApplicantDao = new ApplicantDaoImpl();
        searchMentorDao = new MentorDaoImpl();
        view = new View();
    }

    public void getSearchResult() {
        String search = view.getNotEmptyInputOf("Search:");

        if (view.isInt(search)) {
            int number = view.getInt(search);
            try {
                mentorsSearchResult = searchMentorDao.getMatchingResultFrom(number);
                applicantsSearchResult = searchApplicantDao.getMatchingResultFrom(number);
            } catch (DaoException e) {
                view.printText(e.getMessage());
            }
        } else {
            try {
                mentorsSearchResult = searchMentorDao.getMatchingResultFrom(search);
                applicantsSearchResult = searchApplicantDao.getMatchingResultFrom(search);
            } catch (DaoException e) {
                view.printText(e.getMessage());
            }
        }

        view.printText("\n\nApplicants:");
        if (view.isNotEmpty(applicantsSearchResult)) {
            view.printApplicants(applicantsSearchResult);
        }
        view.printText("\n\nMentors:");
        if (view.isNotEmpty(mentorsSearchResult)) {
            view.printMentors(mentorsSearchResult);
        }
    }
}