package com.decode.newsreporter.infrastructure.configuration;
import com.decode.newsreporter.application.gateway.NewsGateway;
import com.decode.newsreporter.application.parser.NewsParser;
import com.decode.newsreporter.application.usecase.get_all_news_list.GetAllNewsListUsecase;
import com.decode.newsreporter.application.usecase.get_news_by_id.GetNewsByIDUsecase;
import com.decode.newsreporter.application.usecase.get_news_report.GenerateNewsReportUsecase;
import com.decode.newsreporter.application.usecase.submit_news.SubmitNewsUsecase;
import com.decode.newsreporter.domain.repository.NewsRepository;
import com.decode.newsreporter.application.report_generation.ReportGeneration;
import com.decode.newsreporter.infrastructure.factory.NewsFactoryImpl;
import com.decode.newsreporter.infrastructure.repository.NewsRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public SubmitNewsUsecase getSubmitNewsUsecaseInstance(NewsFactoryImpl newsFactory, NewsRepository newsRepository, NewsGateway newsGateway, NewsParser newsParser) {
        return new SubmitNewsUsecase(
                newsFactory,
                newsRepository,
                newsGateway,
                newsParser
        );
    }

    @Bean
    public GenerateNewsReportUsecase getGenerateNewsReportUsecaseInstance(NewsRepositoryImpl newsService, ReportGeneration reportGeneration) {
        return new GenerateNewsReportUsecase(newsService, reportGeneration);
    }

    @Bean
    public GetAllNewsListUsecase getGetNewsListUsecaseInstance(NewsRepositoryImpl newsServiceImpl) {
        return new GetAllNewsListUsecase(newsServiceImpl);
    }

    @Bean
    public GetNewsByIDUsecase getNewsByIDUsecase(NewsRepositoryImpl newsServiceImpl) {
        return new GetNewsByIDUsecase(newsServiceImpl);
    }

}
