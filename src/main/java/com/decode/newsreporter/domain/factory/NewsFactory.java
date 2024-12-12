package com.decode.newsreporter.domain.factory;

import com.decode.newsreporter.domain.entity.News;
import com.decode.newsreporter.domain.value_object.NewsName;
import com.decode.newsreporter.domain.value_object.URL;

public interface NewsFactory {
    News createNews(Long id, URL url, NewsName newsName, String body);
}
