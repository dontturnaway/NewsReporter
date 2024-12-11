package com.decode.newsreporter.Domain.Service.ParsingStrategy;

import com.decode.newsreporter.Domain.Service.ParsedPageBodyDTO;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AbstractNewsStrategy {

    private final String regexp;

    public AbstractNewsStrategy(String regexp) {
        this.regexp = regexp;
    }

    //Get and Return DTO
    public String parseNews(String pageBody) throws CantParseNewsException {

        String regexp = this.regexp;
        System.out.println("Applying regexp to parse news: " + regexp);
        Pattern pattern = Pattern.compile(regexp, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(pageBody);

        if (!matcher.find()) {
            System.out.println("No match found");
            throw new CantParseNewsException();
        }

        String parsedString = matcher.group(1);
        return parsedString;
        }

}
