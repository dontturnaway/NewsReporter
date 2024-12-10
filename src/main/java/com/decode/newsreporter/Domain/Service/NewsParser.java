package com.decode.newsreporter.Domain.Service;

import com.decode.newsreporter.Domain.Service.ParsingStrategy.CantParseNewsException;
import com.decode.newsreporter.Domain.Service.ParsingStrategy.WrongUrlProvided;
import com.decode.newsreporter.Domain.ValueObject.URL;

public interface NewsParser {
    String parseNews(URL url, String body) throws CantParseNewsException, WrongUrlProvided;
}
