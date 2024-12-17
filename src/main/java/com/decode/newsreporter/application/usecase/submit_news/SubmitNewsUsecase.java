package com.decode.newsreporter.application.usecase.submit_news;
import com.decode.newsreporter.application.gateway.CanGetRemoteDataFromURLException;
import com.decode.newsreporter.application.gateway.NewsGateway;
import com.decode.newsreporter.application.gateway.NewsGatewayRequestDTO;
import com.decode.newsreporter.application.gateway.NewsGatewayResponseDTO;
import com.decode.newsreporter.domain.entity.News;
import com.decode.newsreporter.domain.factory.NewsFactory;
import com.decode.newsreporter.domain.service.news_parser.NewsParser;
import com.decode.newsreporter.domain.service.news_parser.NewsParserRequestDTO;
import com.decode.newsreporter.domain.service.NewsService;
import com.decode.newsreporter.domain.service.news_parser.NewsParserResponseDTO;
import com.decode.newsreporter.domain.service.news_parser.parsing_strategy.CantParseNewsException;
import com.decode.newsreporter.domain.service.news_parser.parsing_strategy.WrongUrlProvided;
import com.decode.newsreporter.domain.value_object.NewsName;
import com.decode.newsreporter.domain.value_object.URL;
import com.decode.newsreporter.infrastructure.dto.NewsDTO;

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
