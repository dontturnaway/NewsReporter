package com.decode.newsreporter.domain.service.parsing_strategy;


public class IzRuNewsStrategy extends AbstractNewsStrategy {

    public IzRuNewsStrategy() {
        super("<title>(.*?) \\|.*?</title>");
    }
}
