package com.decode.newsreporter.Application.UseCase.SubmitNews;
import com.decode.newsreporter.Application.UseCase.Gateway.CanGetRemoteDataFromURLException;
import com.decode.newsreporter.Application.UseCase.Gateway.NewsGateway;
import com.decode.newsreporter.Application.UseCase.Gateway.NewsGatewayRequestDTO;
import com.decode.newsreporter.Application.UseCase.Gateway.NewsGatewayResponseDTO;
import com.decode.newsreporter.Domain.Entity.News;
import com.decode.newsreporter.Domain.Factory.NewsFactory;
import com.decode.newsreporter.Domain.Service.NewsParser;
import com.decode.newsreporter.Domain.Service.NewsParserRequestDTO;
import com.decode.newsreporter.Domain.Service.NewsService;
import com.decode.newsreporter.Domain.Service.NewsParserResponseDTO;
import com.decode.newsreporter.Domain.Service.ParsingStrategy.CantParseNewsException;
import com.decode.newsreporter.Domain.Service.ParsingStrategy.WrongUrlProvided;
import com.decode.newsreporter.Domain.ValueObject.NewsName;
import com.decode.newsreporter.Domain.ValueObject.URL;
import com.decode.newsreporter.Infrastructure.Dto.NewsDTO;

public class SubmitNewsUsecase {

    private final NewsFactory newsFactory;
    private final NewsService newsService;
    private final NewsGateway newsGateway;
    private final NewsParser newsParser;

    public SubmitNewsUsecase(NewsFactory newsFactory,
                             NewsService newsService,
                             NewsGateway newsGateway,
                             NewsParser newsParser
    ) {
        this.newsFactory = newsFactory;
        this.newsService = newsService;
        this.newsGateway = newsGateway;
        this.newsParser = newsParser;
    }

    public SubmitNewsResponseDTO submitNews(SubmitNewsRequestDTO submitNewsRequestDTO) throws
                            CanGetRemoteDataFromURLException,
                            CantParseNewsException,
                            WrongUrlProvided {
            URL url = new URL(submitNewsRequestDTO.URL());
            NewsGatewayRequestDTO newsGatewayRequestDTO = new NewsGatewayRequestDTO(url);
            NewsGatewayResponseDTO newsBodyResponse = newsGateway.getNewsFromURL(newsGatewayRequestDTO);
            NewsParserRequestDTO newsParserRequestDTO = new NewsParserRequestDTO(url.getUrl(), newsBodyResponse.response());
            NewsParserResponseDTO parsedNewsName = newsParser.parseNews(newsParserRequestDTO);
            NewsName newsName = new NewsName(parsedNewsName.newsName());
            News news = newsFactory.createNews(null, url, newsName, newsBodyResponse.response());
            NewsDTO savedNews = newsService.save(news);

            return new SubmitNewsResponseDTO(savedNews.id());
    }
}
