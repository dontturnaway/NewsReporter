package com.decode.newsreporter.domain.service;
import com.decode.newsreporter.domain.service.parsing_strategy.CantParseNewsException;
import com.decode.newsreporter.domain.service.parsing_strategy.WrongUrlProvided;


public interface NewsParser {
    NewsParserResponseDTO parseNews(NewsParserRequestDTO newsParserRequestDTO) throws CantParseNewsException, WrongUrlProvided;
}
