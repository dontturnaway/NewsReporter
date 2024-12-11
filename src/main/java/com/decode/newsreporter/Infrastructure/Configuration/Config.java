package com.decode.newsreporter.Infrastructure.Configuration;
import com.decode.newsreporter.Application.UseCase.Gateway.NewsGateway;
import com.decode.newsreporter.Application.UseCase.SubmitNews.SubmitNewsUsecase;
import com.decode.newsreporter.Domain.Repository.NewsRepository;
import com.decode.newsreporter.Domain.Service.NewsParserImpl;
import com.decode.newsreporter.Domain.Service.NewsService;
import com.decode.newsreporter.Infrastructure.Factory.NewsFactoryImpl;
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
