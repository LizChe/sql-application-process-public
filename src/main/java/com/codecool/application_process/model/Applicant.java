package com.codecool.application_process.model;

public class Applicant {
    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private int applicationCode;

    private Applicant(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.phoneNumber = builder.phoneNumber;
        this.email = builder.email;
        this.applicationCode = builder.applicationCode;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public int getApplicationCode() {
        return applicationCode;
    }

    public static class Builder {
        private int id;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private String email;
        private int applicationCode;

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withApplicationCode(int applicationCode) {
            this.applicationCode = applicationCode;
            return this;
        }

        public Applicant build() {
            return new Applicant(this);
        }
    }
}