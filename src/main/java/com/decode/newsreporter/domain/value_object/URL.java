package com.decode.newsreporter.domain.value_object;
import com.decode.newsreporter.infrastructure.service.WrongUrlProvided;
import lombok.Value;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Value
public class URL {

    String url;
    private static final String VALID_URL_REGEX_PATTERN = "((http|https)://)(www.)?"
            + "[a-zA-Z0-9@:%._\\+~#?&//=]"
            + "{2,256}\\.[a-z]"
            + "{2,6}\\b([-a-zA-Z0-9@:%"
            + "._\\+~#?&//=]*)";
    static Pattern pattern;

    static {
        pattern = Pattern.compile(VALID_URL_REGEX_PATTERN);
    }


    public URL(String url) throws WrongUrlProvided {
        if (!assertValidUrl(url)) {
            throw new WrongUrlProvided();
        }
        this.url = url;
    }

    private boolean assertValidUrl(String url) {
        Matcher matcher = pattern.matcher(url);
        return matcher.matches();
    }
}
