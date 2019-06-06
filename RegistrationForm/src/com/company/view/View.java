package com.company.view;

import java.util.Locale;
import java.util.ResourceBundle;

public class View {
    public static final String GREETING = "greeting";
    public static final String FAREWELL = "farewell";

    public static final String MENU = "menu";
    public static final String LANGUAGE_MENU = "language.menu";
    public static final String MENU_ADD_SUBSCRIBER = "menu.add.subscriber";
    public static final String MENU_CNAHGE_LANGUAGE = "menu.change.language";
    public static final String MENU_EXIT = "menu.exit";

    public static final String INPUT_SURNAME = "input.surname";
    public static final String INPUT_NAME = "input.name";
    public static final String INPUT_PATRONYMIC = "input.patronymic";
    public static final String INPUT_NICKNAME = "input.nickname";
    public static final String INPUT_COMMENT = "input.comment";
    public static final String INPUT_GROUP_NAME = "input.group.name";
    public static final String INPUT_HOME_PHONE_NUMBER = "input.phone.home.number";
    public static final String INPUT_MOBILE_PHONE_NUMBER = "input.phone.mobile.number";
    public static final String INPUT_EMAIL_ADDR = "input.email.addr";
    public static final String INPUT_SKYPE_NICKNAME = "input.skype.nickname";

    public static final String INPUT_HOME_ADDR = "input.home.addr";
    public static final String INPUT_HOME_ADDR_INDEX = "input.home.addr.index";
    public static final String INPUT_HOME_ADDR_CITY = "input.home.addr.city";
    public static final String INPUT_HOME_ADDR_STREET = "input.home.addr.street";
    public static final String INPUT_HOME_ADDR_BUILDING_NUMBER = "input.home.addr.building.number";
    public static final String INPUT_HOME_ADDR_APARTMENT_NUMBER = "input.home.addr.apartment.number";


    public static final String UNACCEPTABLE_SURNAME = "unacceptable.surname";
    public static final String UNACCEPTABLE_NAME = "unacceptable.name";
    public static final String UNACCEPTABLE_PATRONYMIC = "unacceptable.patronymic";
    public static final String UNACCEPTABLE_NICKNAME = "unacceptable.nickname";
    public static final String UNACCEPTABLE_GROUP_NAME = "unacceptable.group.name";
    public static final String UNACCEPTABLE_HOME_PHONE_NUMBER = "unacceptable.phone.home.number";
    public static final String UNACCEPTABLE_MOBILE_PHONE_NUMBER = "unacceptable.phone.mobile.number";
    public static final String UNACCEPTABLE_EMAIL_ADDR = "unacceptable.email.addr";
    public static final String UNACCEPTABLE_SKYPE_NICKNAME = "unacceptable.skype.nickname";

    public static final String UNACCEPTABLE_HOME_ADDR_INDEX = "unacceptable.home.addr.index";
    public static final String UNACCEPTABLE_HOME_ADDR_CITY = "unacceptable.home.addr.city";
    public static final String UNACCEPTABLE_HOME_ADDR_STREET = "unacceptable.home.addr.street";
    public static final String UNACCEPTABLE_HOME_ADDR_BUILDING_NUMBER = "unacceptable.home.addr.building.number";
    public static final String UNACCEPTABLE_HOME_ADDR_APARTMENT_NUMBER = "unacceptable.home.addr.apartment.number";

    public static final String INFO_OPTIONAL = "info.optional";
    public static final String INFO_RECORD_ADDED = "info.record.added";

    private ResourceBundle resourceBundle;
    private static final String RESOURCE_NAME = "property.message";

    public View() {
        resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME, Locale.getDefault());
    }

    public View(Locale locale) {
        resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME, locale);
    }

    public void changeResource(Locale locale) {
        resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME, locale);
    }

    public String getString(String key) {
        return resourceBundle.getString(key);
    }

    public void printString(String str) {
        System.out.print(str);
    }

    public void printLine(String line) {
        System.out.println(line);
    }
}
