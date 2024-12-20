package com.decode.newsreporter.infrastructure.factory;
import com.decode.newsreporter.domain.entity.News;
import com.decode.newsreporter.domain.entity.NewsDTO;
import com.decode.newsreporter.domain.factory.NewsFactory;
import org.springframework.stereotype.Component;

@Component
public class NewsFactoryImpl implements NewsFactory {

    @Override
    public NewsDTO createNewsDTO(News news) {
        return new NewsDTO(news.getId(), news.getDate(), news.getURL().getUrl(), news.getNewsName().getName(), news.getBody());
    }
}
