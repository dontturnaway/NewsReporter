package com.decode.newsreporter.domain.repository;
import com.decode.newsreporter.domain.entity.News;

import java.util.List;

public interface NewsRepository {
    List<News> getAllNews();
    News getNewsById(Long id);
    News save(News news);
}
