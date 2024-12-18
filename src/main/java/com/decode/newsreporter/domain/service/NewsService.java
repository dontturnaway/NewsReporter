package com.decode.newsreporter.domain.service;

import com.decode.newsreporter.domain.entity.News;
import com.decode.newsreporter.infrastructure.controller.WrongNewsIdProvided;
import com.decode.newsreporter.infrastructure.entity.NewsDTO;

import java.util.List;

public interface NewsService {
    List<NewsDTO> getAllNews();
    NewsDTO getNewsById(Long id) throws WrongNewsIdProvided;
    List<NewsDTO> getNewsById(List<Long> ids) throws WrongNewsIdProvided;
    NewsDTO save(News news) ;
}
