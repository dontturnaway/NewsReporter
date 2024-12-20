package com.decode.newsreporter.domain.factory;

import com.decode.newsreporter.domain.entity.News;
import com.decode.newsreporter.domain.entity.NewsDTO;

public interface NewsFactory {
    NewsDTO createNewsDTO(News news);
}
