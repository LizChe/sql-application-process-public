package com.codecool.application_process.dao;

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.codecool.application_process.model.Mentor;

public class MentorDaoImpl implements MentorDao {

    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    private List<Mentor> mentors;
    private Mentor mentor;

    public MentorDaoImpl() {
        mentors = new ArrayList<>();
    }

    @Override
    public List<Mentor> getMentorsFullName() {
        connection = getDatabaseConnection();
        mentors.clear();
        String query = "SELECT first_name, last_name "
                     + "FROM mentors";

        try {
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            addMentorsWithFullName(resultSet);

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mentors;
    }

    @Override
    public List<Mentor> getMentorsNickNameFrom(String city) {
        connection = getDatabaseConnection();
        mentors.clear();
        String query = "SELECT nick_name "
                     + "FROM mentors "
                     + "WHERE city = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, city);
            resultSet = statement.executeQuery();

            addMentorsWithNickName(resultSet);

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mentors;
    }

    private void addMentorsWithFullName(ResultSet resultSet) {
        String firstName;
        String lastName;

        try {
            while (resultSet.next()) {
                firstName = resultSet.getString("first_name");
                lastName = resultSet.getString("last_name");

                mentor = new Mentor.Builder()
                        .withFirstName(firstName)
                        .withLastName(lastName)
                        .build();

                mentors.add(mentor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addMentorsWithNickName(ResultSet resultSet) {
        String nickName;

        try {
            while (resultSet.next()) {
                nickName = resultSet.getString("nick_name");

                mentor = new Mentor.Builder()
                        .withNickName(nickName)
                        .build();

                mentors.add(mentor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getDatabaseConnection() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        return databaseConnection.getConnection();
    }
}