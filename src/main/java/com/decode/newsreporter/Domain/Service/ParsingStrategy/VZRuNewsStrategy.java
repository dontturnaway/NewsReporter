package com.decode.newsreporter.Domain.Service.ParsingStrategy;


public class VZRuNewsStrategy extends AbstractNewsStrategy {

    public VZRuNewsStrategy() {
        super("<title>\\RВЗГЛЯД / (.*?) :.*?</title>");
    }
}
