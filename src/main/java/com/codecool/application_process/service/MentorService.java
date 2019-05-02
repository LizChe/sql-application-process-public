package com.codecool.application_process.service;

import java.util.List;

import com.codecool.application_process.model.Mentor;
import com.codecool.application_process.dao.MentorDaoImpl;
import com.codecool.application_process.view.View;


public class MentorService {

    private View view;
    private MentorDaoImpl mentorsDao;
    private List<Mentor> mentors;

    public MentorService() {
        view = new View();
        mentorsDao = new MentorDaoImpl();
    }

    public void getMentorsFullName() {
        mentors = mentorsDao.getMentors();
        for (Mentor mentor : mentors) {
            view.printFormattedText("%n%s %s", mentor.getFirstName(), mentor.getLastName());
        }
    }

    public void getMentorsByChosenCity() {
        view.printText("City:");
        String city = view.getStringInput();
        mentors = mentorsDao.getMentorsFrom(city);
        for (Mentor mentor : mentors) {
            view.printText(mentor.getNickName());
        }
    }
}