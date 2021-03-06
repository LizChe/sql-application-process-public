package com.codecool.application_process.service;

import java.util.List;

import com.codecool.application_process.dao.DaoException;
import com.codecool.application_process.dao.MentorDao;
import com.codecool.application_process.dao.MentorDaoImpl;

import com.codecool.application_process.model.Mentor;

import com.codecool.application_process.view.View;


public class MentorService {

    private View view;
    private MentorDao mentorDao;
    private List<Mentor> mentors;

    public MentorService() {
        view = new View();
        mentorDao = new MentorDaoImpl();
    }

    public void getMentorsFullName() {
        try {
            mentors = mentorDao.getMentors();
        } catch (DaoException e) {
            view.printText(e.getMessage());
        }
        view.printMentorsFullName(mentors);
    }

    public void getMentorsByChosenCity() {
        String city = view.getNotEmptyInputOf("City:");

        try {
            mentors = mentorDao.getMentorsFrom(city);
        } catch (DaoException e) {
            view.printText(e.getMessage());
        }

        if (view.isNotEmpty(mentors)) {
            view.printMentorsNickName(mentors);
        }
    }
}