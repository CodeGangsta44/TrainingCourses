package com.company.controller;

import java.util.ResourceBundle;
import java.util.Locale;
import java.util.regex.Pattern;
import com.company.model.*;
import com.company.model.entity.Address;
import com.company.model.entity.GroupName;
import com.company.model.entity.Subscriber;
import com.company.view.*;
import java.util.InputMismatchException;
import static com.company.view.MessageConstants.*;
import static com.company.controller.PatternConstants.*;

public class Controller {

    private ResourceBundle resourceBundle;
    private static final String RESOURCE_NAME = "regexp";
    private Locale locale;

    private Model model;
    private View view;
    private ConsoleReader reader;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        reader = new ConsoleReader();
        locale = new Locale("en");
        view.changeResource(locale);
        resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME, locale);
    }

    public void execute(){
        view.printLine(view.getString(GREETING));

        boolean userLeft = false;

        while (!userLeft) {
            view.printLine(view.getString(MENU));

            int answer;

            try {
                answer = reader.readInt();
            } catch(InputMismatchException e){
                view.printLine(view.getString(INPUT_MISMATCH));
                reader.resetReader();
                continue;
            }

            switch (answer){
                case 1:
                    reader.resetReader();
                    addSubscriber();
                    break;
                case 2:
                    changeLanguage();
                    break;
                case 3:
                    userLeft = true;
                    break;
            }
        }

        view.printLine(view.getString(FAREWELL));

    }

    private void addSubscriber(){

        Subscriber record = new Subscriber();

        record.setSurname(getMatchableString(view.getString(INPUT_SURNAME),
                view.getString(UNACCEPTABLE_SURNAME),
                resourceBundle.getString(PATTERN_SURNAME)));

        record.setName(getMatchableString(view.getString(INPUT_NAME),
                view.getString(UNACCEPTABLE_NAME),
                resourceBundle.getString(PATTERN_NAME)));

        record.setPatronymic(getMatchableString(view.getString(INPUT_PATRONYMIC),
                view.getString(UNACCEPTABLE_PATRONYMIC),
                resourceBundle.getString(PATTERN_PATRONYMIC)));

        record.setLogin(getMatchableString(view.getString(INPUT_LOGIN),
                view.getString(UNACCEPTABLE_LOGIN),
                resourceBundle.getString(PATTERN_LOGIN)));
        
        record.setComment(getUnmatchableString(view.getString(INPUT_COMMENT)));

        record.setGroupName(getGroupName());

        record.setHomePhoneNumber(getMatchableString(view.getString(INPUT_HOME_PHONE_NUMBER),
                view.getString(UNACCEPTABLE_HOME_PHONE_NUMBER),
                resourceBundle.getString(PATTERN_HOME_PHONE_NUMBER)));

        record.setMobilePhoneNumber(getMatchableString(view.getString(INPUT_MOBILE_PHONE_NUMBER),
                view.getString(UNACCEPTABLE_MOBILE_PHONE_NUMBER),
                resourceBundle.getString(PATTERN_MOBILE_PHONE_NUMBER)));

        record.setMobilePhoneNumber2(getMatchableString(view.getString(INPUT_MOBILE_PHONE_NUMBER)
                        + view.getString(INFO_OPTIONAL),
                view.getString(UNACCEPTABLE_MOBILE_PHONE_NUMBER),
                resourceBundle.getString(PATTERN_MOBILE_PHONE_NUMBER_2)));

        record.setEmail(getMatchableString(view.getString(INPUT_EMAIL_ADDR),
                view.getString(UNACCEPTABLE_EMAIL_ADDR),
                resourceBundle.getString(PATTERN_EMAIL)));

        record.setSkypeNickname(getMatchableString(view.getString(INPUT_SKYPE_NICKNAME),
                view.getString(UNACCEPTABLE_SKYPE_NICKNAME),
                resourceBundle.getString(PATTERN_SKYPE_NICKNAME)));

        record.setAddress(getAddress());


        boolean addedSuccessfully = false;

        while (!addedSuccessfully){
            try{
                model.addRecord(record);
                addedSuccessfully = true;
            } catch (LoginNotUniqueException e) {
                view.printLine(view.getString(EXCEPTION_NOT_UNIQUE_LOGIN) + e.getLogin());

                record.setLogin(getMatchableString(view.getString(INPUT_LOGIN),
                        view.getString(UNACCEPTABLE_LOGIN),
                        resourceBundle.getString(PATTERN_LOGIN)));
            }
        }

        view.printLine(view.getString(INFO_RECORD_ADDED));
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
        String answer = getMatchableString(view.getString(INPUT_GROUP_NAME),
                view.getString(UNACCEPTABLE_GROUP_NAME),
                resourceBundle.getString(PATTERN_GROUP_NAME));

        GroupName groupName = GroupName.UNDEFINED;
        if (Pattern.matches(resourceBundle.getString(PATTERN_GROUP_FAMILY), answer)){
            groupName = GroupName.FAMILY;
        }

        if (Pattern.matches(resourceBundle.getString(PATTERN_GROUP_FRIENDS), answer)){
            groupName = GroupName.FRIENDS;
        }

        if (Pattern.matches(resourceBundle.getString(PATTERN_GROUP_WORK), answer)){
            groupName = GroupName.WORK;
        }
        System.out.println(groupName);
        return groupName;
    }

    private Address getAddress(){
        String index = getMatchableString(view.getString(INPUT_HOME_ADDR_INDEX),
                view.getString(UNACCEPTABLE_HOME_ADDR_INDEX),
                resourceBundle.getString(PATTERN_POST_INDEX));

        String city = getMatchableString(view.getString(INPUT_HOME_ADDR_CITY),
                view.getString(UNACCEPTABLE_HOME_ADDR_CITY),
                resourceBundle.getString(PATTERN_CITY_NAME));

        String street = getMatchableString(view.getString(INPUT_HOME_ADDR_STREET),
                view.getString(UNACCEPTABLE_HOME_ADDR_STREET),
                resourceBundle.getString(PATTERN_STREET_NAME));

        String buildingNumber = getMatchableString(view.getString(INPUT_HOME_ADDR_BUILDING_NUMBER),
                view.getString(UNACCEPTABLE_HOME_ADDR_BUILDING_NUMBER),
                resourceBundle.getString(PATTERN_BUILDING_NUMBER));

        String apartmentNumber = getMatchableString(view.getString(INPUT_HOME_ADDR_APARTMENT_NUMBER),
                view.getString(UNACCEPTABLE_HOME_ADDR_APARTMENT_NUMBER),
                resourceBundle.getString(PATTERN_APARTMENT_NUMBER));

        return new Address(index, city, street, buildingNumber, apartmentNumber);
    }

    private void changeLanguage(){
        view.printLine(view.getString(LANGUAGE_MENU));

        switch (reader.readInt()){
            case 1:
                locale = new Locale("en");
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
