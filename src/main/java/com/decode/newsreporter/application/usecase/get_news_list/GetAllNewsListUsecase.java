/* Сценарий 2. Получение списка новостей. Система возвращает список (массив) ранее созданных сущностей с полями:
- ID, дата, URL, название новости */
package com.decode.newsreporter.application.usecase.get_news_list;
import com.decode.newsreporter.domain.service.NewsService;

public class GetAllNewsListUsecase {

    private final NewsService newsService;

    public GetAllNewsListUsecase(NewsService newsService) {
        this.newsService = newsService;
    }

    public GetNewsListResponse getNewsList() {
        return new GetNewsListResponse(newsService.getAllNews());
    }

}
