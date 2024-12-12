package com.decode.newsreporter.domain.service.parsing_strategy;

/* Tested */
public class LentaRuNewsStrategy extends AbstractNewsStrategy {

    public LentaRuNewsStrategy() {
        super("<title>(.*?): ");
    }
}
