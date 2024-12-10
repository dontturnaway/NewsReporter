package com.decode.newsreporter.Domain.ValueObject;

import com.decode.newsreporter.Domain.Service.ParsingStrategy.WrongUrlProvided;

import static java.util.regex.Pattern.matches;

public final class URL {

    private final String url;
    private static final String VALID_URL_REGEX = "((http|https)://)(www.)?"
            + "[a-zA-Z0-9@:%._\\+~#?&//=]"
            + "{2,256}\\.[a-z]"
            + "{2,6}\\b([-a-zA-Z0-9@:%"
            + "._\\+~#?&//=]*)";

    public URL(String url) throws WrongUrlProvided {
        if (!assertValidUrl(url)) {
            throw new WrongUrlProvided();
        }
        this.url = url;
    }

    private boolean assertValidUrl(String url) {
        return matches(VALID_URL_REGEX, url);
    }

    public String getUrl() {
        return url;
    }

}
