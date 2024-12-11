package com.decode.newsreporter.Domain.Service;
import com.decode.newsreporter.Domain.Service.ParsingStrategy.CantParseNewsException;
import com.decode.newsreporter.Domain.Service.ParsingStrategy.WrongUrlProvided;


public interface NewsParser {
    NewsParserResponseDTO parseNews(NewsParserRequestDTO newsParserRequestDTO) throws CantParseNewsException, WrongUrlProvided;
}
