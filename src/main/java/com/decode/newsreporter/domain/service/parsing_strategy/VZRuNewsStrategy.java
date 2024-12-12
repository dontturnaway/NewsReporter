package com.decode.newsreporter.domain.service.parsing_strategy;


public class VZRuNewsStrategy extends AbstractNewsStrategy {

    public VZRuNewsStrategy() {
        super("<title>\\RВЗГЛЯД / (.*?) :.*?</title>");
    }
}
