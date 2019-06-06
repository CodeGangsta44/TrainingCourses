package com.company.controller;

import java.awt.desktop.SystemSleepEvent;
import java.util.ResourceBundle;
import java.util.Locale;
import java.util.regex.Pattern;
import com.company.model.*;
import com.company.view.*;

public class Controller {
    public static final String PATTERN_SURNAME = "pattern.surname";
    public static final String PATTERN_NAME = "pattern.name";
    public static final String PATTERN_PATRONYMIC = "pattern.patronymic";
    public static final String PATTERN_NICKNAME = "pattern.nickname";

    public static final String PATTERN_GROUP_NAME = "pattern.group.name";
    public static final String PATTERN_GROUP_FAMILY = "pattern.group.family";
    public static final String PATTERN_GROUP_FRIENDS = "pattern.group.friends";
    public static final String PATTERN_GROUP_WORK = "pattern.group.work";

    public static final String PATTERN_HOME_PHONE_NUMBER = "pattern.home.phone.number";
    public static final String PATTERN_MOBILE_PHONE_NUMBER = "pattern.mobile.phone.number";
    public static final String PATTERN_MOBILE_PHONE_NUMBER_2 = "pattern.mobile.phone.number.2";
    public static final String PATTERN_EMAIL = "pattern.email";
    public static final String PATTERN_SKYPE_NICKNAME = "pattern.skype.nickname";

    public static final String PATTERN_POST_INDEX = "pattern.post.index";
    public static final String PATTERN_CITY_NAME = "pattern.city.name";
    public static final String PATTERN_STREET_NAME = "pattern.street.name";
    public static final String PATTERN_BUILDING_NUMBER = "pattern.building.number";
    public static final String PATTERN_APARTMENT_NUMBER = "pattern.apartment.number";



    private ResourceBundle resourceBundle;
    private static final String RESOURCE_NAME = "property.regexp";
    private Locale locale;

    private Model model;
    private View view;
    private ConsoleReader reader;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        reader = new ConsoleReader();
        locale = Locale.getDefault();
        view.changeResource(locale);
        resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME, locale);
    }

    public void execute(){
        view.printLine(view.getString(View.GREETING));

        menuLoop: while (true) {
            view.printLine(view.getString(View.MENU));

            switch (reader.readInt()){
                case 1:
                    reader.resetReader();
                    addSubscriber();
                    break;
                case 2:
                    changeLanguage();
                    break;
                case 3:
                    break menuLoop;
            }
        }

        view.printLine(view.getString(View.FAREWELL));

    }

    private void addSubscriber(){
        Subscriber record = new Subscriber();

        record.setSurname(getMatchableString(view.getString(View.INPUT_SURNAME),
                view.getString(View.UNACCEPTABLE_SURNAME),
                resourceBundle.getString(Controller.PATTERN_SURNAME)));

        record.setName(getMatchableString(view.getString(View.INPUT_NAME),
                view.getString(View.UNACCEPTABLE_NAME),
                resourceBundle.getString(Controller.PATTERN_NAME)));

        record.setPatronymic(getMatchableString(view.getString(View.INPUT_PATRONYMIC),
                view.getString(View.UNACCEPTABLE_PATRONYMIC),
                resourceBundle.getString(Controller.PATTERN_PATRONYMIC)));

        record.setNickname(getMatchableString(view.getString(View.INPUT_NICKNAME),
                view.getString(View.UNACCEPTABLE_NICKNAME),
                resourceBundle.getString(Controller.PATTERN_NICKNAME)));
        
        record.setComment(getUnmatchableString(view.getString(View.INPUT_COMMENT)));

        record.setGroupName(getGroupName());

        record.setHomePhoneNumber(getMatchableString(view.getString(View.INPUT_HOME_PHONE_NUMBER),
                view.getString(View.UNACCEPTABLE_HOME_PHONE_NUMBER),
                resourceBundle.getString(Controller.PATTERN_HOME_PHONE_NUMBER)));

        record.setMobilePhoneNumber(getMatchableString(view.getString(View.INPUT_MOBILE_PHONE_NUMBER),
                view.getString(View.UNACCEPTABLE_MOBILE_PHONE_NUMBER),
                resourceBundle.getString(Controller.PATTERN_MOBILE_PHONE_NUMBER)));

        record.setMobilePhoneNumber2(getMatchableString(view.getString(View.INPUT_MOBILE_PHONE_NUMBER)
                        + view.getString(View.INFO_OPTIONAL),
                view.getString(View.UNACCEPTABLE_MOBILE_PHONE_NUMBER),
                resourceBundle.getString(Controller.PATTERN_MOBILE_PHONE_NUMBER_2)));

        record.setEmail(getMatchableString(view.getString(View.INPUT_EMAIL_ADDR),
                view.getString(View.UNACCEPTABLE_EMAIL_ADDR),
                resourceBundle.getString(Controller.PATTERN_EMAIL)));

        record.setSkypeNickname(getMatchableString(view.getString(View.INPUT_SKYPE_NICKNAME),
                view.getString(View.UNACCEPTABLE_SKYPE_NICKNAME),
                resourceBundle.getString(Controller.PATTERN_SKYPE_NICKNAME)));

        record.setAddress(getAddress());

        model.addRecord(record);
        view.printLine(view.getString(View.INFO_RECORD_ADDED));
    }

    private String getMatchableString(String inputMessage, String unacceptableMessage, String patternString) {
        String answer;

        while (true) {
            view.printString(inputMessage);
            answer = reader.readString();

            if (Pattern.matches(patternString, answer)) {
                break;
            }

            view.printLine(unacceptableMessage);
        }
        return answer;
    }

    private String getUnmatchableString(String inputMessage) {
        view.printString(inputMessage);
        return reader.readString();
    }

    private GroupName getGroupName() {
        String answer = getMatchableString(view.getString(View.INPUT_GROUP_NAME),
                view.getString(View.UNACCEPTABLE_GROUP_NAME),
                resourceBundle.getString(Controller.PATTERN_GROUP_NAME));

        GroupName groupName = GroupName.UNDEFINED;
        if (Pattern.matches(resourceBundle.getString(Controller.PATTERN_GROUP_FAMILY), answer)){
            groupName = GroupName.FAMILY;
        }

        if (Pattern.matches(resourceBundle.getString(Controller.PATTERN_GROUP_FRIENDS), answer)){
            groupName = GroupName.FRIENDS;
        }

        if (Pattern.matches(resourceBundle.getString(Controller.PATTERN_GROUP_WORK), answer)){
            groupName = GroupName.WORK;
        }
        System.out.println(groupName);
        return groupName;
    }

    private Address getAddress(){
        String index = getMatchableString(view.getString(View.INPUT_HOME_ADDR_INDEX),
                view.getString(View.UNACCEPTABLE_HOME_ADDR_INDEX),
                resourceBundle.getString(Controller.PATTERN_POST_INDEX));

        String city = getMatchableString(view.getString(View.INPUT_HOME_ADDR_CITY),
                view.getString(View.UNACCEPTABLE_HOME_ADDR_CITY),
                resourceBundle.getString(Controller.PATTERN_CITY_NAME));

        String street = getMatchableString(view.getString(View.INPUT_HOME_ADDR_STREET),
                view.getString(View.UNACCEPTABLE_HOME_ADDR_STREET),
                resourceBundle.getString(Controller.PATTERN_STREET_NAME));

        String buildingNumber = getMatchableString(view.getString(View.INPUT_HOME_ADDR_BUILDING_NUMBER),
                view.getString(View.UNACCEPTABLE_HOME_ADDR_BUILDING_NUMBER),
                resourceBundle.getString(Controller.PATTERN_BUILDING_NUMBER));

        String apartmentNumber = getMatchableString(view.getString(View.INPUT_HOME_ADDR_APARTMENT_NUMBER),
                view.getString(View.UNACCEPTABLE_HOME_ADDR_APARTMENT_NUMBER),
                resourceBundle.getString(Controller.PATTERN_APARTMENT_NUMBER));

        return new Address(index, city, street, buildingNumber, apartmentNumber);
    }

    private void changeLanguage(){
        view.printLine(view.getString(View.LANGUAGE_MENU));

        switch (reader.readInt()){
            case 1:
                locale = Locale.getDefault();
                break;
            case 2:
                locale = new Locale("ua");
                break;
            case 3:
                locale = new Locale("ru");
                break;
        }

        view.changeResource(locale);
        resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME, locale);

    }
}
