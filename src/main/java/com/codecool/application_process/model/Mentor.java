package com.codecool.application_process.model;

public class Mentor {
    private int id;
    private String firstName;
    private String lastName;
    private String nickName;
    private String phoneNumber;
    private String email;
    private String city;
    private int favouriteNumber;

    private Mentor(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.nickName = builder.nickName;
        this.phoneNumber = builder.phoneNumber;
        this.email = builder.email;
        this.city = builder.city;
        this.favouriteNumber = builder.favouriteNumber;
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

    public String getNickName() {
        return nickName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }

    public int getFavouriteNumber() {
        return favouriteNumber;
    }

    public static class Builder {
        private int id;
        private String firstName;
        private String lastName;
        private String nickName;
        private String phoneNumber;
        private String email;
        private String city;
        private int favouriteNumber;

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

        public Builder withNickName(String nickName) {
            this.nickName = nickName;
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

        public Builder withCity(String city) {
            this.city = city;
            return this;
        }

        public Builder withFavouriteNumber(int favouriteNumber) {
            this.favouriteNumber = favouriteNumber;
            return this;
        }

        public Mentor build() {
            return  new Mentor(this);
        }
    }
}