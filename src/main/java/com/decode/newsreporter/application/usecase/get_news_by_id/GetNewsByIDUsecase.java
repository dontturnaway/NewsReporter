/* Сценарий 4. Добавил получение по ID новости */

package com.decode.newsreporter.application.usecase.get_news_by_id;
import com.decode.newsreporter.domain.service.NewsService;
import com.decode.newsreporter.infrastructure.controller.WrongNewsIdProvided;

public class GetNewsByIDUsecase {

    private final NewsService newsService;

    public GetNewsByIDUsecase(NewsService newsService) {
        this.newsService = newsService;
    }

    public GetNewsByIdResponse getNewsList(GetNewsByIDRequest request) throws WrongNewsIdProvided {
        return new GetNewsByIdResponse(newsService.getNewsById(request.newsId()));
    }

}
