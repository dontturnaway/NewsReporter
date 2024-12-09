package com.decode.newsreporter.Domain.Service;

import com.decode.newsreporter.Domain.Service.ParsingStrategy.CantParseNewsException;
import com.decode.newsreporter.Domain.Service.ParsingStrategy.IncorrectUrlProvidedForParsing;

public interface NewsParser {
    String parseNews(String url, String body) throws CantParseNewsException, IncorrectUrlProvidedForParsing;
}
