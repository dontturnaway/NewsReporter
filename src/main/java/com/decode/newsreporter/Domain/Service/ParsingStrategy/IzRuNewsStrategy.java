package com.decode.newsreporter.Domain.Service.ParsingStrategy;


public class IzRuNewsStrategy extends AbstractNewsStrategy {

    public IzRuNewsStrategy() {
        super("<title>(.*?) \\|.*?</title>");
    }
}
