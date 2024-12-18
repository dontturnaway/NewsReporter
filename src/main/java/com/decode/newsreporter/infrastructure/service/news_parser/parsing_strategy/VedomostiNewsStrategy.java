package com.decode.newsreporter.infrastructure.service.news_parser.parsing_strategy;


public class VedomostiNewsStrategy extends AbstractNewsStrategy {

    public VedomostiNewsStrategy() {
        super("<title>(.*?) -.*?</title>");
    }
}
