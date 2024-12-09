package com.decode.newsreporter.Domain.ValueObject;

import static java.util.regex.Pattern.matches;

public final class Name {

    private String name;

    public Name(String name) {
        if (!assertValidName(name)) {
            throw new IllegalArgumentException("Name should contain at least 3 letter");
        }
    }

    public String getName() {
        return name;
    }

    private boolean assertValidName(String name) {
        return matches("^(?=.*[a-zA-Z]).{4,}$", name);
    }

}
