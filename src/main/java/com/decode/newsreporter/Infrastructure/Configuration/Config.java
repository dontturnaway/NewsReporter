package com.decode.newsreporter.Infrastructure.Configuration;
import com.decode.newsreporter.Application.UseCase.SubmitNews.SubmitNewsUsecase;
import com.decode.newsreporter.Domain.Repository.NewsRepositoryInterface;
import com.decode.newsreporter.Domain.Service.NewsParserImpl;
import com.decode.newsreporter.Infrastructure.Factory.NewsFactory;
import com.decode.newsreporter.Infrastructure.Repository.Gateway.NewsGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public SubmitNewsUsecase getSubmitNewsUsecaseInstance(NewsFactory newsFactory, NewsRepositoryInterface newsRepository, NewsGateway newsGateway) {
        return new SubmitNewsUsecase(
                newsFactory,
                newsRepository,
                newsGateway,
                getNewsParserInstance(newsFactory)
        );
    }

    @Bean
    NewsParserImpl getNewsParserInstance(NewsFactory newsFactory) {
        return new NewsParserImpl();
    }
}
