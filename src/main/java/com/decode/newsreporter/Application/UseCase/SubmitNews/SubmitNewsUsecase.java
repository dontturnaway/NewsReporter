package com.decode.newsreporter.Application.UseCase.SubmitNews;
import com.decode.newsreporter.Application.UseCase.Gateway.CanGetRemoteDataFromURLException;
import com.decode.newsreporter.Application.UseCase.Gateway.NewsGatewayInterface;
import com.decode.newsreporter.Domain.Entity.News;
import com.decode.newsreporter.Domain.Factory.NewsFactoryInterface;
import com.decode.newsreporter.Domain.Repository.NewsRepositoryInterface;
import com.decode.newsreporter.Domain.Service.NewsParser;
import com.decode.newsreporter.Domain.Service.ParsingStrategy.CantParseNewsException;
import com.decode.newsreporter.Domain.Service.ParsingStrategy.IncorrectUrlProvidedForParsing;

public class SubmitNewsUsecase {

    private final NewsFactoryInterface newsFactoryInterface;
    private final NewsRepositoryInterface newsRepositoryInterface;
    private final NewsGatewayInterface newsGatewayInterface;
    private final NewsParser newsParser;

    public SubmitNewsUsecase(NewsFactoryInterface newsFactoryInterface,
                             NewsRepositoryInterface newsRepositoryInterface,
                             NewsGatewayInterface newsGatewayInterface,
                             NewsParser newsParser
    ) {
        this.newsFactoryInterface = newsFactoryInterface;
        this.newsRepositoryInterface = newsRepositoryInterface;
        this.newsGatewayInterface = newsGatewayInterface;
        this.newsParser = newsParser;
    }

    public SubmitNewsResponse submitNews(SubmitNewsRequest submitNewsRequest) throws
                            CanGetRemoteDataFromURLException,
                            CantParseNewsException,
                            IncorrectUrlProvidedForParsing {
            String url = submitNewsRequest.URL();

            String newsBody = newsGatewayInterface.getNewsFromURL(url);
            String parsedNewsName = newsParser.parseNews(url, newsBody);
            News news = newsFactoryInterface.createNews(parsedNewsName, url, newsBody);
            News savedNews = newsRepositoryInterface.save(news);
            return new SubmitNewsResponse(savedNews);
    }
}
