package com.company.model;

import java.util.Date;


public class Subscriber {
    private String surname;
    private String name;
    private String patronymic;
    private String fullName;

    private String nickname;
    private String comment;

    public GroupName groupName;

    private String homePhoneNumber;
    private String mobilePhoneNumber;
    private String mobilePhoneNumber2;
    private String email;
    private String skypeNickname;

    private Address address;

    private Date dateOfEntryIntoBook;
    private Date dateOfLastModification;

    private void updateLastModificationDate() {
        dateOfLastModification = new Date();
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public Date getDateOfEntryIntoBook() {
        return dateOfEntryIntoBook;
    }

    public void setDateOfEntryIntoBook(Date dateOfEntryIntoBook) {
        this.dateOfEntryIntoBook = dateOfEntryIntoBook;
        updateLastModificationDate();
    }

    public Date getDateOfLastModification() {
        return dateOfLastModification;
    }

    public GroupName getGroupName() {
        return groupName;
    }

    public void setGroupName(GroupName groupName) {
        this.groupName = groupName;
    }
}
