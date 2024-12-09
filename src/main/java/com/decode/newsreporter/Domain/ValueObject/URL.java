package com.decode.newsreporter.Domain.ValueObject;

import static java.util.regex.Pattern.matches;

public final class URL {

    private String url;

    public URL(String url) {
        if (!assertValidUrl(url)) {
            throw new IllegalArgumentException("URL should start from \"http\"");
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
