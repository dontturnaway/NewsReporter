package com.decode.newsreporter.domain.repository;
import com.decode.newsreporter.domain.entity.News;

import java.util.List;

public interface NewsRepository {
    List<News> getAllNews();
    News getNewsById(Long id);
    List<News> getNewsById(List<Long> newsIds);
    News save(News news);
}
