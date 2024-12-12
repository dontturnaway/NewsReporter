package com.decode.newsreporter.domain.service.parsing_strategy;


public class GeneralNewsStrategy extends AbstractNewsStrategy {

    public GeneralNewsStrategy() {
        super("<title>(.*?)</title>");
    }

}
