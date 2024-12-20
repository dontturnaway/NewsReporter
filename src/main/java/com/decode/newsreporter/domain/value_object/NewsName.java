package com.decode.newsreporter.domain.value_object;
import lombok.Value;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Value
public class NewsName {

    String name;
    static String VALID_NAME_REGEX_PATTERN="^.{4,}$";
    static Pattern pattern;

    static {
        pattern = Pattern.compile(VALID_NAME_REGEX_PATTERN);
    }

    public NewsName(String name) {
        if (!assertValidName(name)) {
            throw new IllegalArgumentException("Name should contain at least 3 letter");
        }
        this.name = name;
    }

    private boolean assertValidName(String name) {
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

}
