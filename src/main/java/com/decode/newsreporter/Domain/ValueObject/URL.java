package com.decode.newsreporter.Domain.ValueObject;

import com.decode.newsreporter.Domain.Service.ParsingStrategy.WrongUrlProvided;

import static java.util.regex.Pattern.matches;

public final class URL {

    private final String url;

    public URL(String url) throws WrongUrlProvided {
        if (!assertValidUrl(url)) {
            throw new WrongUrlProvided();
        }
        this.url = url;
    }

    private boolean assertValidUrl(String url) {
        return matches("^http", url);
    }

    public String getUrl() {
        return url;
    }

}
