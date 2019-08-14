package ua.dovhopoliuk.springtask.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

public class RegExPatterns {
    public static final String SURNAME = "^([A-Z].[a-z]{1,30}(-[A-Z].[a-z]{1,30})?)|([А-ЩЬЮЯЇІЄҐ].[а-щьюяїієґ]{1,30}(-[А-ЩЬЮЯЇІЄҐ].[а-щьюяїієґ]{1,30})?)$";
    public static final String NAME = "^([A-Z].[a-z]{1,20}(-[A-Z].[a-z]{1,20})?)|([А-ЩЬЮЯЇІЄҐ].[а-щьюяїієґ]{1,20}(-[А-ЩЬЮЯЇІЄҐ].[а-щьюяїієґ]{1,20})?)$";
    public static final String PATRONYMIC = "^([A-Z].[a-z]{1,20})|([А-ЩЬЮЯЇІЄҐ].[а-щьюяїієґ]{1,20})$";
    public static final String LOGIN = "^[A-Za-z0-9_.]{8,50}$";
    public static final String EMAIL = "^[A-Za-z0-9_.]+@[a-z]+(\\.[a-z]+)+$";
    public static final String PASSWORD = "^[A-Za-z0-9_.]{8,20}$";
}
