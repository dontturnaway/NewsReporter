package com.decode.newsreporter.domain.service;

import com.decode.newsreporter.domain.entity.News;
import com.decode.newsreporter.infrastructure.controller.exceptions.WrongNewsId;
import com.decode.newsreporter.infrastructure.dto.NewsDTO;

import java.util.List;

public interface NewsService {
    List<NewsDTO> getAllNews();
    NewsDTO getNewsById(Long id) throws WrongNewsId; //React in service
    NewsDTO save(News news) throws IllegalArgumentException;
}
