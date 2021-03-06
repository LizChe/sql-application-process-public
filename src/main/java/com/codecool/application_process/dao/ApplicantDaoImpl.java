package com.codecool.application_process.dao;

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.codecool.application_process.model.Applicant;

public class ApplicantDaoImpl implements ApplicantDao, SearchDao<Applicant> {

    @Override
    public int create(Applicant applicant) throws DaoException {

        String query = "INSERT INTO applicants "
                + "(first_name, last_name, phone_number, email, application_code) "
                + "VALUES(?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            setRequiredFieldsFrom(preparedStatement, applicant);

            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Failed to add the applicant.\n" + e);
        }
    }

    @Override
    public int update(Applicant applicant) throws DaoException {

        String query = "UPDATE applicants "
                + "SET first_name = ?, last_name = ?, phone_number = ?, "
                + "email = ?, application_code = ?"
                + "WHERE LOWER(first_name) LIKE LOWER(?) "
                + "AND LOWER(last_name) LIKE LOWER(?)";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            setRequiredFieldsFrom(preparedStatement, applicant);
            preparedStatement.setString(6, applicant.getFirstName());
            preparedStatement.setString(7, applicant.getLastName());

            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Failed to update the applicant: "
                    + applicant.getFirstName() + " " + applicant.getLastName() + "\n" + e);
        }
    }

    @Override
    public int deleteApplicantBy(String email) throws DaoException {

        String query = "DELETE FROM applicants WHERE email LIKE ?";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, "%" + email + "%");

            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Failed to delete the applicant(s) with email: "
                    + email + "\n" + e);
        }
    }

    @Override
    public List<Applicant> getApplicantBy(String firstName, String lastName) throws DaoException {

        List<Applicant> applicants;
        String query = "SELECT * "
                + "FROM applicants "
                + "WHERE LOWER(first_name) = LOWER(?) "
                + "AND LOWER(last_name) = LOWER(?)";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            applicants = getApplicantsFrom(preparedStatement);

        } catch (SQLException e) {
            throw new DaoException("Failed to build the list of applicants.\n" + e);
        }
        return applicants;
    }

    @Override
    public List<Applicant> getApplicantBy(int applicationCode) throws DaoException {

        List<Applicant> applicants;
        String query = "SELECT * "
                + "FROM applicants "
                + "WHERE application_code = ?";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, applicationCode);
            applicants = getApplicantsFrom(preparedStatement);

        } catch (SQLException e) {
            throw new DaoException("Failed to get the applicant with the application code: "
                    + applicationCode + "\n" + e);
        }
        return applicants;
    }

    @Override
    public List<Applicant> getApplicantsByFirstName(String firstName) throws DaoException {
        return getApplicantsBy("first_name", firstName);
    }

    @Override
    public List<Applicant> getApplicantsByEmail(String email) throws DaoException {
        email = "%" + email + "%";
        return getApplicantsBy("email", email);
    }

    @Override
    public List<Applicant> getMatchingResultFrom(String userInput) throws DaoException {

        List<Applicant> applicants;
        userInput = "%" + userInput + "%";
        String query = "SELECT * FROM applicants "
                + "WHERE LOWER(first_name) LIKE LOWER(?) "
                + "OR LOWER(last_name) LIKE LOWER(?) "
                + "OR phone_number LIKE ? "
                + "OR LOWER(email) LIKE LOWER(?)";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, userInput);
            preparedStatement.setString(2, userInput);
            preparedStatement.setString(3, userInput);
            preparedStatement.setString(4, userInput);
            applicants = getApplicantsFrom(preparedStatement);

        } catch (SQLException e) {
            throw new DaoException("Failed to build the list of matching results from applicants.\n"
                    + e);
        }
        return applicants;
    }

    @Override
    public List<Applicant> getMatchingResultFrom(int userInput) throws DaoException {

        List<Applicant> applicants;
        String query = "SELECT * FROM applicants "
                + "WHERE id = ?"
                + "OR application_code = ? "
                + "OR phone_number LIKE ?";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, userInput);
            preparedStatement.setInt(2, userInput);
            preparedStatement.setString(3, "%" + userInput + "%");
            applicants = getApplicantsFrom(preparedStatement);

        } catch (SQLException e) {
            throw new DaoException("Failed to build the list of matching results from applicants.\n"
                    + e);
        }
        return applicants;
    }

    private List<Applicant> getApplicantsFrom(PreparedStatement preparedStatement) throws DaoException {

        Applicant applicant;
        List<Applicant> applicants = new ArrayList<>();

        int ID;
        String firstName;
        String lastName;
        String phoneNumber;
        String email;
        int applicationCode;

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                ID = resultSet.getInt("id");
                firstName = resultSet.getString("first_name");
                lastName = resultSet.getString("last_name");
                phoneNumber = resultSet.getString("phone_number");
                email = resultSet.getString("email");
                applicationCode = resultSet.getInt("application_code");

                applicant = new Applicant.Builder()
                        .withId(ID)
                        .withFirstName(firstName)
                        .withLastName(lastName)
                        .withPhoneNumber(phoneNumber)
                        .withEmail(email)
                        .withApplicationCode(applicationCode)
                        .build();

                applicants.add(applicant);
            }

        } catch (SQLException e) {
            throw new DaoException("Failed to populate the list of applicants.\n" + e);
        }
        return applicants;
    }

    private void setRequiredFieldsFrom(PreparedStatement preparedStatement, Applicant applicant) throws SQLException {
        preparedStatement.setString(1, applicant.getFirstName());
        preparedStatement.setString(2, applicant.getLastName());
        preparedStatement.setString(3, applicant.getPhoneNumber());
        preparedStatement.setString(4, applicant.getEmail());
        preparedStatement.setInt(5, applicant.getApplicationCode());
    }

    private List<Applicant> getApplicantsBy(String column, String value) throws DaoException {

        List<Applicant> applicants;
        String query = "SELECT * "
                + "FROM applicants "
                + "WHERE LOWER(" + column + ") LIKE LOWER(?)";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, value);
            applicants = getApplicantsFrom(preparedStatement);

        } catch (SQLException e) {
            throw new DaoException("Failed to get applicant with the : "
                    + value + "\n" + e);
        }
        return applicants;
    }
}