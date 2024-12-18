package com.decode.newsreporter.infrastructure.service.news_parser.parsing_strategy;

/* Tested */
public class LentaRuNewsStrategy extends AbstractNewsStrategy {

    public LentaRuNewsStrategy() {
        super("<title>(.*?): ");
    }
}
