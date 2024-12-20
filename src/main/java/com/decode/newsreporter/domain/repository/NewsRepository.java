package com.decode.newsreporter.domain.repository;

import com.decode.newsreporter.infrastructure.controller.WrongNewsIdProvided;
import com.decode.newsreporter.domain.entity.NewsDTO;

import java.util.List;

public interface NewsRepository {
    List<NewsDTO> getAllNews();
    NewsDTO getNewsById(Long id) throws WrongNewsIdProvided;
    List<NewsDTO> getNewsById(List<Long> ids) throws WrongNewsIdProvided;
    NewsDTO save(NewsDTO news) ;
}
