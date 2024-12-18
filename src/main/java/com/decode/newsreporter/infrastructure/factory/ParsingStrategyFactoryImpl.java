package com.decode.newsreporter.infrastructure.factory;
import com.decode.newsreporter.domain.factory.ParsingStrategyFactory;
import com.decode.newsreporter.domain.value_object.URL;
import com.decode.newsreporter.infrastructure.service.WrongUrlProvided;
import com.decode.newsreporter.infrastructure.service.news_parser.parsing_strategy.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
public class ParsingStrategyFactoryImpl implements ParsingStrategyFactory {

    private static final String PARSE_HTTP_REGEXP = "https?://(?:www\\.)?([\\w.-]+)";

    public AbstractNewsStrategy getConcreteFactory(URL url) throws CantParseNewsException, WrongUrlProvided {
        String clearUrl = extractSiteNameFromUrl(url.getUrl());

        AbstractNewsStrategy strategy = new GeneralNewsStrategy();
        log.info("Applying strategy for: {}", clearUrl);
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
        return strategy;
    }

    private String extractSiteNameFromUrl(String url) throws WrongUrlProvided {
        Pattern pattern = Pattern.compile(PARSE_HTTP_REGEXP);
        Matcher matcher = pattern.matcher(url);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            throw new WrongUrlProvided();
        }
    }

}
