package com.decode.newsreporter.domain.value_object;
import lombok.Value;
import static java.util.regex.Pattern.matches;

@Value
public class NewsName {

    String name;

    public NewsName(String name) {
        if (!assertValidName(name)) {
            throw new IllegalArgumentException("Name should contain at least 3 letter");
        }
        this.name = name;
    }

    private boolean assertValidName(String name) {
        return matches("^(?=.*[a-zA-Z]).{4,}$", name);
    }

}
