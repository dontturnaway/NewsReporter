package com.decode.newsreporter.domain.repository;

import com.decode.newsreporter.domain.entity.NewsDTO;

import java.util.List;

public interface NewsRepository {
    List<NewsDTO> getAllNews();
    NewsDTO getNewsById(Long id);
    List<NewsDTO> getNewsById(List<Long> ids);
    NewsDTO save(NewsDTO news) ;
}
