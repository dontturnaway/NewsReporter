package com.decode.newsreporter.application.parser;
import com.decode.newsreporter.infrastructure.service.news_parser.parsing_strategy.CantParseNewsException;
import com.decode.newsreporter.infrastructure.service.WrongUrlProvided;

public interface NewsParserService {
    NewsParserResponseDTO parseNews(NewsParserRequestDTO newsParserRequestDTO) throws CantParseNewsException, WrongUrlProvided;
}
