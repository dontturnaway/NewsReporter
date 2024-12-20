/* Сценарий 1. Добавление новости. В систему передаётся URL новостного материала в Интернете. Система скачивает HTML по этому URL и создаёт на его основе сущность со следующими полями:
    - дата (текущая дата)
    - URL (нам его передали в запросе)
    - название новости (его проще всего взять из тега title)
    В ответ возвращается ID сущности. */

package com.decode.newsreporter.application.usecase.submit_news;
import com.decode.newsreporter.application.gateway.CanGetRemoteDataFromURLException;
import com.decode.newsreporter.application.gateway.NewsGateway;
import com.decode.newsreporter.application.gateway.NewsGatewayRequestDTO;
import com.decode.newsreporter.application.gateway.NewsGatewayResponseDTO;
import com.decode.newsreporter.domain.entity.News;
import com.decode.newsreporter.domain.factory.NewsFactory;
import com.decode.newsreporter.application.parser.NewsParserService;
import com.decode.newsreporter.application.parser.NewsParserRequestDTO;
import com.decode.newsreporter.domain.service.NewsService;
import com.decode.newsreporter.application.parser.NewsParserResponseDTO;
import com.decode.newsreporter.infrastructure.service.news_parser.parsing_strategy.CantParseNewsException;
import com.decode.newsreporter.infrastructure.service.WrongUrlProvided;
import com.decode.newsreporter.domain.value_object.NewsName;
import com.decode.newsreporter.domain.value_object.URL;
import com.decode.newsreporter.infrastructure.entity.NewsDTO;

public class SubmitNewsUsecase {

    private final NewsFactory newsFactory;
    private final NewsService newsService;
    private final NewsGateway newsGateway;
    private final NewsParserService newsParserService;

    public SubmitNewsUsecase(NewsFactory newsFactory,
                             NewsService newsService,
                             NewsGateway newsGateway,
                             NewsParserService newsParserService
    ) {
        this.newsFactory = newsFactory;
        this.newsService = newsService;
        this.newsGateway = newsGateway;
        this.newsParserService = newsParserService;
    }

    public SubmitNewsResponseDTO submitNews(SubmitNewsRequestDTO submitNewsRequestDTO) throws
                            CanGetRemoteDataFromURLException,
                            CantParseNewsException,
                            WrongUrlProvided {
            URL url = new URL(submitNewsRequestDTO.URL());
            NewsGatewayRequestDTO newsGatewayRequestDTO = new NewsGatewayRequestDTO(url);
            NewsGatewayResponseDTO newsBodyResponse = newsGateway.getNewsFromURL(newsGatewayRequestDTO);
            NewsParserRequestDTO newsParserRequestDTO = new NewsParserRequestDTO(url.getUrl(), newsBodyResponse.response());
            NewsParserResponseDTO parsedNewsName = newsParserService.parseNews(newsParserRequestDTO);

            NewsName newsName = new NewsName(parsedNewsName.newsName());
            News news = newsFactory.createNews(null, url, newsName, newsBodyResponse.response());
            NewsDTO savedNews = newsService.save(news);

            return new SubmitNewsResponseDTO(savedNews.id());
    }
}
