package com.decode.newsreporter.infrastructure.service.news_parser.parsing_strategy;


public class IzRuNewsStrategy extends AbstractNewsStrategy {

    public IzRuNewsStrategy() {
        super("<title>(.*?) \\|.*?</title>");
    }
}
