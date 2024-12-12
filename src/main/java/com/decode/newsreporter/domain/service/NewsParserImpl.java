package com.decode.newsreporter.domain.service;
import com.decode.newsreporter.domain.service.parsing_strategy.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewsParserImpl implements NewsParser {

    private AbstractNewsStrategy strategy;
    private static final String PARSE_HTTP_REGEXP = "https?://(?:www\\.)?([\\w.-]+)";

    @Override
    public NewsParserResponseDTO parseNews(NewsParserRequestDTO newsParserRequestDTO) throws CantParseNewsException, WrongUrlProvided {
        String urlToParse = newsParserRequestDTO.url();
        if (urlToParse == null || urlToParse.isEmpty()) {
            throw new WrongUrlProvided();
        }
        this.setNewsStrategy(urlToParse);
        String parsedNews = strategy.parseNews(newsParserRequestDTO.body());
        return new NewsParserResponseDTO(parsedNews);
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
