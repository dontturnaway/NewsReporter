package com.decode.newsreporter.domain.service.news_parser.parsing_strategy;


public class VedomostiNewsStrategy extends AbstractNewsStrategy {

    public VedomostiNewsStrategy() {
        super("<title>(.*?) -.*?</title>");
    }
}
