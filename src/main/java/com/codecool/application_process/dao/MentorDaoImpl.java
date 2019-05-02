package com.codecool.application_process.dao;

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.codecool.application_process.model.Mentor;

public class MentorDaoImpl implements MentorDao, SearchDao<Mentor> {

    @Override
    public List<Mentor> getMentors() throws DaoException {

        List<Mentor> mentors;
        String query = "SELECT * "
                     + "FROM mentors";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            mentors = getMentorsFrom(preparedStatement);

        } catch (SQLException e) {
            throw new DaoException("Failed to build the list of mentors.\n" + e);
        }
        return mentors;
    }

    @Override
    public List<Mentor> getMentorsFrom(String city) throws DaoException {

        List<Mentor> mentors;
        String query = "SELECT * "
                + "FROM mentors "
                + "WHERE city = ?";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, city);
            mentors = getMentorsFrom(preparedStatement);

        } catch (SQLException e) {
            throw new DaoException("Failed to build list of mentors from: " + city + ".\n" + e);
        }
        return mentors;
    }

    @Override
    public List<Mentor> getMatchingResultFrom(String userInput) throws DaoException {

        List<Mentor> mentors;
        String query = "SELECT * FROM mentors "
                + "WHERE first_name LIKE ? "
                + "OR last_name LIKE ? "
                + "OR nick_name LIKE ?"
                + "OR phone_number LIKE ? "
                + "OR email LIKE ? "
                + "OR city LIKE ?";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, userInput);
            preparedStatement.setString(2, userInput);
            preparedStatement.setString(3, userInput);
            preparedStatement.setString(4, userInput);
            preparedStatement.setString(5, userInput);
            preparedStatement.setString(6, userInput);
            mentors = getMentorsFrom(preparedStatement);

        } catch (SQLException e) {
            throw new DaoException("Failed to build the list of matching results from mentors.\n"
                    + e);
        }
        return mentors;
    }

    @Override
    public List<Mentor> getMatchingResultFrom(int userInput) throws DaoException {

        List<Mentor> mentors;
        String query = "SELECT * FROM mentors "
                + "WHERE id = ?"
                + "OR favourite_number = ?";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, userInput);
            preparedStatement.setInt(2, userInput);
            mentors = getMentorsFrom(preparedStatement);

        } catch (SQLException e) {
            throw new DaoException("Failed to build the list of matching results from mentors.\n"
                    + e);
        }
        return mentors;
    }

    private List<Mentor> getMentorsFrom(PreparedStatement preparedStatement) {

        List<Mentor> mentors = new ArrayList<>();
        Mentor mentor;

        int ID;
        String firstName;
        String lastName;
        String nickName;
        String phoneNumber;
        String email;
        String city;
        int favouriteNumber;

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                ID = resultSet.getInt("id");
                firstName = resultSet.getString("first_name");
                lastName = resultSet.getString("last_name");
                nickName = resultSet.getString("nick_name");
                phoneNumber = resultSet.getString("phone_number");
                email = resultSet.getString("email");
                city = resultSet.getString("city");
                favouriteNumber = resultSet.getInt("favourite_number");

                mentor = new Mentor.Builder()
                        .withId(ID)
                        .withFirstName(firstName)
                        .withLastName(lastName)
                        .withNickName(nickName)
                        .withPhoneNumber(phoneNumber)
                        .withEmail(email)
                        .withCity(city)
                        .withFavouriteNumber(favouriteNumber)
                        .build();

                mentors.add(mentor);
            }

        } catch (SQLException e) {
            throw new DaoException("Failed to populate the list of mentors.\n" + e);
        }
        return mentors;
    }
}