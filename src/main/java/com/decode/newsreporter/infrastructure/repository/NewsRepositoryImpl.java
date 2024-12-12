package com.decode.newsreporter.infrastructure.repository;

import com.decode.newsreporter.domain.entity.News;
import com.decode.newsreporter.domain.repository.NewsRepository;
import com.decode.newsreporter.infrastructure.controller.exceptions.WrongNewsId;
import com.decode.newsreporter.infrastructure.entity.NewsORM;
import com.decode.newsreporter.infrastructure.factory.NewsConvertFactory;
import com.decode.newsreporter.infrastructure.factory.ORMNewsConvertFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class NewsRepositoryImpl implements NewsRepository {

    private final NewsRepositoryORM newsRepositoryORM;
    private final NewsConvertFactory newsConvertFactory;
    private final ORMNewsConvertFactory ormNewsConvertFactory;

    public NewsRepositoryImpl(NewsRepositoryORM newsRepositoryORM,
                              NewsConvertFactory newsConvertFactory,
                              ORMNewsConvertFactory ormNewsConvertFactory
                          ) {
        this.newsRepositoryORM = newsRepositoryORM;
        this.newsConvertFactory = newsConvertFactory;
        this.ormNewsConvertFactory = ormNewsConvertFactory;
    }

    @Override
    public List<News> getAllNews() {
        List <News> newsList = new ArrayList<>();
        newsRepositoryORM.findAll().forEach(news ->
                newsList.add(newsConvertFactory.createNews(news))
        );
        return newsList;
    }

    @Override
    public News getNewsById(Long id) throws WrongNewsId {
        NewsORM news = newsRepositoryORM.findById(id).orElseThrow(WrongNewsId::new);
        return newsConvertFactory.createNews(news);
    }

    @Override
    public News save(News news) {
        NewsORM newsConverted = ormNewsConvertFactory.createORMNews(news);
        NewsORM savedItem = newsRepositoryORM.save(newsConverted);
        return newsConvertFactory.createNews(savedItem);
    }
}
