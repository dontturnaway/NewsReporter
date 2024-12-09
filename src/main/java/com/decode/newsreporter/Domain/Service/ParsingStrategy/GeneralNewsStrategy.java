package com.decode.newsreporter.Domain.Service.ParsingStrategy;


public class GeneralNewsStrategy extends AbstractNewsStrategy {

    public GeneralNewsStrategy() {
        super("<title>(.*?)</title>");
    }

}
