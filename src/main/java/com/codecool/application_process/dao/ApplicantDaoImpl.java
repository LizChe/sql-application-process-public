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
    public void create(Applicant applicant) throws DaoException {

        String query = "INSERT INTO applicants "
                + "(first_name, last_name, phone_number, email, application_code) "
                + "VALUES(?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, applicant.getFirstName());
            preparedStatement.setString(2, applicant.getLastName());
            preparedStatement.setString(3, applicant.getPhoneNumber());
            preparedStatement.setString(4, applicant.getEmail());
            preparedStatement.setInt(5, applicant.getApplicationCode());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Failed to add the applicant.\n" + e);
        }
    }

    @Override
    public void update(Applicant applicant) throws DaoException {

        String query = "UPDATE applicants "
                + "SET first_name = ?, last_name = ?, phone_number = ?, "
                + "email = ?, application_code = ?"
                + "WHERE first_name = ? AND last_name = ?";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, applicant.getFirstName());
            preparedStatement.setString(2, applicant.getLastName());
            preparedStatement.setString(3, applicant.getPhoneNumber());
            preparedStatement.setString(4, applicant.getEmail());
            preparedStatement.setInt(5, applicant.getApplicationCode());
            preparedStatement.setString(6, applicant.getFirstName());
            preparedStatement.setString(7, applicant.getLastName());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Failed to update the applicant: "
                    + applicant.getFirstName() + " " + applicant.getLastName() + "\n" + e);
        }
    }

    @Override
    public void deleteApplicantBy(String email) throws DaoException {

        String query = "DELETE FROM applicants WHERE email LIKE ?";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, "%" + email + "%");
            preparedStatement.executeUpdate();

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
                + "WHERE first_name = ? AND last_name = ?";

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

        List<Applicant> applicants;
        String query = "SELECT * "
                + "FROM applicants "
                + "WHERE first_name = ?";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, firstName);
            applicants = getApplicantsFrom(preparedStatement);

        } catch (SQLException e) {
            throw new DaoException("Failed to get the applicant with the name: "
                    + firstName + "\n" + e);
        }
        return applicants;
    }

    @Override
    public List<Applicant> getApplicantsByEmail(String email) throws DaoException {

        List<Applicant> applicants;
        String query = "SELECT * "
                + "FROM applicants "
                + "WHERE email LIKE ?";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, "%" + email + "%");
            applicants = getApplicantsFrom(preparedStatement);

        } catch (SQLException e) {
            throw new DaoException("Failed to get the applicant with the email: "
                    + email + "\n" + e);
        }
        return applicants;
    }

    @Override
    public List<Applicant> getMatchingResultFrom(String userInput) throws DaoException {

        List<Applicant> applicants;
        userInput = "%" + userInput + "%";
        String query = "SELECT * FROM applicants "
                + "WHERE first_name LIKE ? "
                + "OR last_name LIKE ? "
                + "OR phone_number LIKE ? "
                + "OR email LIKE ?";

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
                + "OR application_code = ?";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, userInput);
            preparedStatement.setInt(2, userInput);
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
}