package com.decode.newsreporter.Domain.Service.ParsingStrategy;

/* Tested */
public class LentaRuNewsStrategy extends AbstractNewsStrategy {

    public LentaRuNewsStrategy() {
        super("<title>(.*?): ");
    }
}
