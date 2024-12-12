package com.decode.newsreporter.infrastructure.factory;
import com.decode.newsreporter.domain.entity.News;
import com.decode.newsreporter.domain.factory.NewsFactory;
import com.decode.newsreporter.domain.value_object.NewsName;
import com.decode.newsreporter.domain.value_object.URL;
import org.springframework.stereotype.Component;

@Component
public class NewsFactoryImpl implements NewsFactory {

    @Override
    public News createNews(Long id, URL url, NewsName newsName, String body) {
        return new News(id, url, newsName, body);
    }
}
