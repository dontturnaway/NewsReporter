package com.decode.newsreporter.infrastructure.service.news_parser.parsing_strategy;


public class GeneralNewsStrategy extends AbstractNewsStrategy {

    public GeneralNewsStrategy() {
        super("<title>(.*?)</title>");
    }

}
