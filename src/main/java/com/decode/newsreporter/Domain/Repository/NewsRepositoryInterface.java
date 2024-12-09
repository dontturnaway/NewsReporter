package com.decode.newsreporter.Domain.Repository;


import com.decode.newsreporter.Domain.Entity.News;

import java.util.List;

public interface NewsRepositoryInterface {
    List<News> getAllNews();
    News getNewsById(Long id);
    News save(News news) throws IllegalArgumentException;
}
