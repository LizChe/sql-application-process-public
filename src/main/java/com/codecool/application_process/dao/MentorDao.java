package com.codecool.application_process.dao;

import java.util.List;

import com.codecool.application_process.model.Mentor;

public interface MentorDao {
    public List<Mentor> getMentorsFullName();
    public List<Mentor> getMentorsNickNameFrom(String city);
}