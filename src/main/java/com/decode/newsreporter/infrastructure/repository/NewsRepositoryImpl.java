package com.decode.newsreporter.infrastructure.repository;

import com.decode.newsreporter.domain.entity.News;
import com.decode.newsreporter.domain.repository.NewsRepository;
import com.decode.newsreporter.infrastructure.entity.NewsORM;
import com.decode.newsreporter.infrastructure.factory.NewsConvertFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class NewsRepositoryImpl implements NewsRepository {

    private final NewsRepositoryORM newsRepositoryORM;
    private final NewsConvertFactory newsConvertFactory;

    public NewsRepositoryImpl(NewsRepositoryORM newsRepositoryORM,
                              NewsConvertFactory newsConvertFactory
                          ) {
        this.newsRepositoryORM = newsRepositoryORM;
        this.newsConvertFactory = newsConvertFactory;
    }

    @Override
    public List<News> getAllNews() {
        return newsConvertFactory.convertNewsFromORM(newsRepositoryORM.findAll());
    }

    @Override
    public News getNewsById(Long id) {
        Optional<NewsORM> news = newsRepositoryORM.findById(id);
        NewsORM result = news.orElse(null);
        return (result != null) ? newsConvertFactory.convertNewsFromORM(result) : null;
    }

    @Override
    public List<News> getNewsById(List<Long> newsIds) {
        List<NewsORM> newsORMList = newsRepositoryORM.findAllById(newsIds);
        return newsConvertFactory.convertNewsFromORM(newsORMList);
    }

    @Override
    public News save(News news) {
        NewsORM newsConverted = newsConvertFactory.convertNewsToORM(news);
        NewsORM savedItem = newsRepositoryORM.save(newsConverted);
        return newsConvertFactory.convertNewsFromORM(savedItem);
    }
}
