package com.codecool.application_process.dao;

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.codecool.application_process.model.Applicant;

public class ApplicantDaoImpl implements ApplicantDao {

    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    private List<Applicant> applicants;
    private Applicant applicant;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public ApplicantDaoImpl() {
        applicants = new ArrayList<>();
    }

    @Override
    public List<Applicant> getPhoneNumberBy(String firstNameOrEmail) {
        connection = getDatabaseConnection();
        applicants.clear();
        String query;

        if (firstNameOrEmail.contains("@")) {
            query = "SELECT first_name, last_name, phone_number "
                    + "FROM applicants "
                    + "WHERE email LIKE ?";
            firstNameOrEmail = "%" + firstNameOrEmail;
        } else {
            query = "SELECT first_name, last_name, phone_number "
                    + "FROM applicants "
                    + "WHERE first_name = ?";
        }

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, firstNameOrEmail);
            resultSet = statement.executeQuery();

            addApplicantsWithFullNameAndPhoneNumber(resultSet);

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applicants;
    }

    @Override
    public List<Applicant> getApplicantBy(int applicationCode) {
        connection = getDatabaseConnection();
        applicants.clear();
        String query = "SELECT * "
                     + "FROM applicants "
                     + "WHERE application_code = ?";
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, applicationCode);
            resultSet = statement.executeQuery();

            addApplicants(resultSet);

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applicants;
    }

    @Override
    public List<Applicant> getApplicantBy(String name, String surname) {
        connection = getDatabaseConnection();
        applicants.clear();
        String query = "SELECT * "
                + "FROM applicants "
                + "WHERE first_name = ? AND last_name = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, surname);
            resultSet = statement.executeQuery();

            addApplicants(resultSet);

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applicants;
    }

    @Override
    public void addNew(Applicant applicant) {
        connection = getDatabaseConnection();
        String query = "INSERT INTO applicants "
                + "(first_name, last_name, phone_number, email, application_code) "
                + "VALUES(?, ?, ?, ?, ?)";

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, applicant.getFirstName());
            statement.setString(2, applicant.getLastName());
            statement.setString(3, applicant.getPhoneNumber());
            statement.setString(4, applicant.getEmail());
            statement.setInt(5, applicant.getApplicationCode());
            statement.executeUpdate();

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateApplicant(String phoneNumber, String name, String surname) {
        connection = getDatabaseConnection();
        String query = "UPDATE applicants "
                     + "SET phone_number = ? "
                     + "WHERE first_name = ? AND last_name = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, phoneNumber);
            statement.setString(2, name);
            statement.setString(3, surname);
            statement.executeUpdate();

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteApplicantsBy(String email) {
        connection = getDatabaseConnection();
        String query = "DELETE FROM applicants WHERE email LIKE ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, "%" + email);
            statement.executeUpdate();

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getDatabaseConnection() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        return databaseConnection.getConnection();
    }

    private void addApplicants(ResultSet resultSet) {
        int id;
        int applicationCode;
        String email;

        try {
            while (resultSet.next()) {
                id = resultSet.getInt("id");
                firstName = resultSet.getString("first_name");
                lastName = resultSet.getString("last_name");
                phoneNumber = resultSet.getString("phone_number");
                email = resultSet.getString("email");
                applicationCode = resultSet.getInt("application_code");

                applicant = new Applicant.Builder()
                        .withId(id)
                        .withFirstName(firstName)
                        .withLastName(lastName)
                        .withPhoneNumber(phoneNumber)
                        .withEmail(email)
                        .withApplicationCode(applicationCode)
                        .build();

                applicants.add(applicant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addApplicantsWithFullNameAndPhoneNumber(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                firstName = resultSet.getString("first_name");
                lastName = resultSet.getString("last_name");
                phoneNumber = resultSet.getString("phone_number");

                applicant = new Applicant.Builder()
                        .withFirstName(firstName)
                        .withLastName(lastName)
                        .withPhoneNumber(phoneNumber)
                        .build();

                applicants.add(applicant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}