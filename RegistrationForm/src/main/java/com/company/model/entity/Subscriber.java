package com.company.model.entity;

import java.time.LocalDate;
import java.util.Date;


public class Subscriber {
    private int id;

    private String surname;
    private String name;
    private String patronymic;
    private String fullName;

    private String login;
    private String comment;

    private GroupName groupName;

    private String homePhoneNumber;
    private String mobilePhoneNumber;
    private String mobilePhoneNumber2;
    private String email;
    private String skypeNickname;

    private Address address;

    private LocalDate dateOfEntryIntoBook;
    private LocalDate dateOfLastModification;

    private void updateLastModificationDate() {
        dateOfLastModification = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
        updateLastModificationDate();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        updateLastModificationDate();
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
        updateLastModificationDate();
    }

    public String getFullName() {
        return fullName;
    }

    public void concatFullName() {
        fullName = surname + " " + name.charAt(0) + '.';
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
        updateLastModificationDate();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
        updateLastModificationDate();
    }

    public String getHomePhoneNumber() {
        return homePhoneNumber;
    }

    public void setHomePhoneNumber(String homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
        updateLastModificationDate();
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
        updateLastModificationDate();
    }

    public String getMobilePhoneNumber2() {
        return mobilePhoneNumber2;
    }

    public void setMobilePhoneNumber2(String mobilePhoneNumber2) {
        this.mobilePhoneNumber2 = mobilePhoneNumber2;
        updateLastModificationDate();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        updateLastModificationDate();
    }

    public String getSkypeNickname() {
        return skypeNickname;
    }

    public void setSkypeNickname(String skypeNickname) {
        this.skypeNickname = skypeNickname;
        updateLastModificationDate();
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
        updateLastModificationDate();
    }

    public LocalDate getDateOfEntryIntoBook() {
        return dateOfEntryIntoBook;
    }

    public void setDateOfEntryIntoBook(LocalDate dateOfEntryIntoBook) {
        this.dateOfEntryIntoBook = dateOfEntryIntoBook;
        updateLastModificationDate();
    }

    public LocalDate getDateOfLastModification() {
        return dateOfLastModification;
    }

    public void setDateOfLastModification(LocalDate dateOfLastModification) {
        this.dateOfLastModification = dateOfLastModification;
    }

    public GroupName getGroupName() {
        return groupName;
    }

    public void setGroupName(GroupName groupName) {
        this.groupName = groupName;
    }
}
