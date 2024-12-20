package com.decode.newsreporter.infrastructure.service;
import com.decode.newsreporter.domain.factory.ParsingStrategyFactory;
import com.decode.newsreporter.application.parser.NewsParserRequestDTO;
import com.decode.newsreporter.application.parser.NewsParserResponseDTO;
import com.decode.newsreporter.application.parser.NewsParserService;
import com.decode.newsreporter.domain.value_object.URL;
import com.decode.newsreporter.infrastructure.service.news_parser.parsing_strategy.*;
import org.springframework.stereotype.Service;

@Service
public class NewsParserServiceImpl implements NewsParserService {

    private final ParsingStrategyFactory factory;

    public NewsParserServiceImpl(ParsingStrategyFactory factory) {
        this.factory = factory;
    }

    @Override
    public NewsParserResponseDTO parseNews(NewsParserRequestDTO newsParserRequestDTO) throws CantParseNewsException, WrongUrlProvided {
        String urlFromDTO = newsParserRequestDTO.url();
        URL urlToParse = new URL(urlFromDTO);

        AbstractNewsStrategy strategy= factory.getConcreteFactory(urlToParse);
        String parsedNews = strategy.parseNews(newsParserRequestDTO.body());
        return new NewsParserResponseDTO(parsedNews);
    }

}
