package com.decode.newsreporter.domain.factory;

import com.decode.newsreporter.domain.value_object.URL;
import com.decode.newsreporter.infrastructure.service.WrongUrlProvided;
import com.decode.newsreporter.infrastructure.service.news_parser.parsing_strategy.AbstractNewsStrategy;
import com.decode.newsreporter.infrastructure.service.news_parser.parsing_strategy.CantParseNewsException;

public interface ParsingStrategyFactory {

    AbstractNewsStrategy getConcreteFactory(URL url) throws CantParseNewsException, WrongUrlProvided;

}
