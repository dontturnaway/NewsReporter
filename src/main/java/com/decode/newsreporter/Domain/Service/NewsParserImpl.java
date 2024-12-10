package com.decode.newsreporter.Domain.Service;

import com.decode.newsreporter.Domain.Service.ParsingStrategy.*;
import com.decode.newsreporter.Domain.ValueObject.URL;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewsParserImpl implements NewsParser {

    private AbstractNewsStrategy strategy;
    private static final String PARSE_HTTP_REGEXP = "https?://(?:www\\.)?([\\w.-]+)";

    @Override
    public String parseNews(URL url, String body) throws CantParseNewsException, WrongUrlProvided {
        String urlToParse = url.getUrl();
        if (urlToParse == null || urlToParse.isEmpty()) {
            throw new WrongUrlProvided();
        }
        this.setNewsStrategy(urlToParse);
        return strategy.parseNews(body);
    }

    private void setNewsStrategy(String url) throws WrongUrlProvided {
        String clearUrl = getNewsSiteFromUrl(url);
        System.out.println("Applying strategy for: " + clearUrl);
        switch (clearUrl) {
            case "iz.ru":
                strategy = new IzRuNewsStrategy();
                break;
            case "lenta.ru":
                strategy = new LentaRuNewsStrategy();
                break;
            case "vz.ru":
                strategy = new VZRuNewsStrategy();
                break;
            case "vedomosti.ru":
                strategy = new VedomostiNewsStrategy();
                break;
            case null:
                break;
            default:
                strategy= new GeneralNewsStrategy();
        }
    }

    private String getNewsSiteFromUrl(String url) throws WrongUrlProvided {
        Pattern pattern = Pattern.compile(PARSE_HTTP_REGEXP);
        Matcher matcher = pattern.matcher(url);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            throw new WrongUrlProvided();
        }
    }

}
