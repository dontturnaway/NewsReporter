package com.decode.newsreporter.infrastructure.configuration;
import com.decode.newsreporter.application.usecase.gateway.NewsGateway;
import com.decode.newsreporter.application.usecase.submitNews.SubmitNewsUsecase;
import com.decode.newsreporter.domain.service.NewsParserImpl;
import com.decode.newsreporter.domain.service.NewsService;
import com.decode.newsreporter.infrastructure.factory.NewsFactoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public SubmitNewsUsecase getSubmitNewsUsecaseInstance(NewsFactoryImpl newsFactoryImpl, NewsService newsService, NewsGateway newsGateway) {
        return new SubmitNewsUsecase(
                newsFactoryImpl,
                newsService,
                newsGateway,
                getNewsParserInstance(newsFactoryImpl)
        );
    }

    @Bean
    NewsParserImpl getNewsParserInstance(NewsFactoryImpl newsFactoryImpl) {
        return new NewsParserImpl();
    }
}
