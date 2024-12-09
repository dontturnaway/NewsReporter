package com.decode.newsreporter.Domain.Service.ParsingStrategy;


public class VedomostiNewsStrategy extends AbstractNewsStrategy {

    public VedomostiNewsStrategy() {
        super("<title>(.*?) -.*?</title>");
    }
}
