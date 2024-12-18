package com.decode.newsreporter.application.usecase.get_news_list;

/*

Сценарий 2. Получение списка новостей. Система возвращает список (массив) ранее созданных сущностей с полями:
- ID
- дата
- URL
- название новости

 */

import com.decode.newsreporter.domain.service.NewsService;
import com.decode.newsreporter.infrastructure.dto.NewsDTO;

import java.util.List;

public class GetNewsListUsecase {
    private final NewsService newsService;
    public GetNewsListUsecase(NewsService newsService) {
        this.newsService = newsService;
    }

    public List<NewsDTO> getNewsList() {
        return newsService.getAllNews();
    }

}
