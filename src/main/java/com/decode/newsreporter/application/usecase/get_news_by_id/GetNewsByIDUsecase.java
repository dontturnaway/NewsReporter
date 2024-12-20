/* Сценарий 4. Добавил получение по ID новости */

package com.decode.newsreporter.application.usecase.get_news_by_id;
import com.decode.newsreporter.domain.repository.NewsRepository;
import com.decode.newsreporter.infrastructure.controller.WrongNewsIdProvided;

public class GetNewsByIDUsecase {

    private final NewsRepository newsRepository;

    public GetNewsByIDUsecase(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public GetNewsByIdResponse getNewsList(GetNewsByIDRequest request) throws WrongNewsIdProvided {
        return new GetNewsByIdResponse(newsRepository.getNewsById(request.newsId()));
    }

}
