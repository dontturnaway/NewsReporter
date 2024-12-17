package com.decode.newsreporter.domain.service.news_parser;
import com.decode.newsreporter.domain.service.news_parser.parsing_strategy.CantParseNewsException;
import com.decode.newsreporter.domain.service.news_parser.parsing_strategy.WrongUrlProvided;

public interface NewsParser {
    NewsParserResponseDTO parseNews(NewsParserRequestDTO newsParserRequestDTO) throws CantParseNewsException, WrongUrlProvided;
}
