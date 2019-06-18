package com.company.view;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class View {

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

    public void printCollection(List<?> list){
        for (Object i : list) {
            printString(i.toString() + '\n');
        }
    }
}
