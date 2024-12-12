package com.decode.newsreporter.domain.service.parsing_strategy;


public class VedomostiNewsStrategy extends AbstractNewsStrategy {

    public VedomostiNewsStrategy() {
        super("<title>(.*?) -.*?</title>");
    }
}
