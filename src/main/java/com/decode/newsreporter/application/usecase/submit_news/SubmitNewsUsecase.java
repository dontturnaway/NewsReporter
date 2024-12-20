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
import com.decode.newsreporter.application.parser.NewsParser;
import com.decode.newsreporter.application.parser.NewsParserRequestDTO;
import com.decode.newsreporter.domain.repository.NewsRepository;
import com.decode.newsreporter.application.parser.NewsParserResponseDTO;
import com.decode.newsreporter.infrastructure.service.news_parser.parsing_strategy.CantParseNewsException;
import com.decode.newsreporter.infrastructure.service.WrongUrlProvided;
import com.decode.newsreporter.domain.value_object.NewsName;
import com.decode.newsreporter.domain.value_object.URL;
import com.decode.newsreporter.domain.entity.NewsDTO;

public class SubmitNewsUsecase {

    private final NewsFactory newsFactory;
    private final NewsRepository newsRepository;
    private final NewsGateway newsGateway;
    private final NewsParser newsParser;

    public SubmitNewsUsecase(NewsFactory newsFactory,
                             NewsRepository newsRepository,
                             NewsGateway newsGateway,
                             NewsParser newsParser
    ) {
        this.newsFactory = newsFactory;
        this.newsRepository = newsRepository;
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
            News news = new News(null, url, newsName, newsBodyResponse.response());
            NewsDTO newsDTO = newsFactory.createNewsDTO(news);
            NewsDTO savedNews = newsRepository.save(newsDTO);

            return new SubmitNewsResponseDTO(savedNews.id());
    }
}
