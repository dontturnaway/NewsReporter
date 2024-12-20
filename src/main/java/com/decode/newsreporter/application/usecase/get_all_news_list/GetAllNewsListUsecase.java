/* Сценарий 2. Получение списка новостей. Система возвращает список (массив) ранее созданных сущностей с полями:
- ID, дата, URL, название новости */
package com.decode.newsreporter.application.usecase.get_all_news_list;
import com.decode.newsreporter.domain.service.NewsRepository;

public class GetAllNewsListUsecase {

    private final NewsRepository newsRepository;

    public GetAllNewsListUsecase(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public GetNewsListResponse getNewsList() {
        return new GetNewsListResponse(newsRepository.getAllNews());
    }

}
