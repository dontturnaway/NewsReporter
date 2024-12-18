package com.decode.newsreporter.domain.service;

import com.decode.newsreporter.domain.entity.News;
import com.decode.newsreporter.infrastructure.controller.WrongNewsId;
import com.decode.newsreporter.infrastructure.dto.NewsDTO;

import java.util.List;

public interface NewsService {
    List<NewsDTO> getAllNews();
    NewsDTO getNewsById(Long id) throws WrongNewsId;
    List<NewsDTO> getNewsById(List<Long> ids) throws WrongNewsId;
    NewsDTO save(News news) ;
}
