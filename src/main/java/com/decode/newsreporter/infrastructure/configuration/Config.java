package com.decode.newsreporter.infrastructure.configuration;
import com.decode.newsreporter.application.gateway.NewsGateway;
import com.decode.newsreporter.application.parser.NewsParserService;
import com.decode.newsreporter.application.usecase.get_all_news_list.GetAllNewsListUsecase;
import com.decode.newsreporter.application.usecase.get_news_by_id.GetNewsByIDUsecase;
import com.decode.newsreporter.application.usecase.get_news_report.GenerateNewsReportUsecase;
import com.decode.newsreporter.application.usecase.submit_news.SubmitNewsUsecase;
import com.decode.newsreporter.domain.service.NewsService;
import com.decode.newsreporter.application.report_generation.ReportGenerationService;
import com.decode.newsreporter.infrastructure.factory.NewsFactoryImpl;
import com.decode.newsreporter.infrastructure.service.NewsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public SubmitNewsUsecase getSubmitNewsUsecaseInstance(NewsFactoryImpl newsFactory, NewsService newsService, NewsGateway newsGateway, NewsParserService newsParserService) {
        return new SubmitNewsUsecase(
                newsFactory,
                newsService,
                newsGateway,
                newsParserService
        );
    }

    @Bean
    public GenerateNewsReportUsecase getGenerateNewsReportUsecaseInstance(NewsServiceImpl newsService, ReportGenerationService reportGenerationService) {
        return new GenerateNewsReportUsecase(newsService, reportGenerationService);
    }

    @Bean
    public GetAllNewsListUsecase getGetNewsListUsecaseInstance(NewsServiceImpl newsServiceImpl) {
        return new GetAllNewsListUsecase(newsServiceImpl);
    }

    @Bean
    public GetNewsByIDUsecase getNewsByIDUsecase(NewsServiceImpl newsServiceImpl) {
        return new GetNewsByIDUsecase(newsServiceImpl);
    }

}
