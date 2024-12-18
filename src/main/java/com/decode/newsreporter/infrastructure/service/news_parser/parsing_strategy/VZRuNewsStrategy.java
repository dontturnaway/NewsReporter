package com.decode.newsreporter.infrastructure.service.news_parser.parsing_strategy;


public class VZRuNewsStrategy extends AbstractNewsStrategy {

    public VZRuNewsStrategy() {
        super("<title>\\RВЗГЛЯД / (.*?) :.*?</title>");
    }
}
