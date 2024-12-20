package com.decode.newsreporter.infrastructure.repository;

import com.decode.newsreporter.domain.service.NewsRepository;
import com.decode.newsreporter.domain.entity.NewsDTO;
import com.decode.newsreporter.infrastructure.entity.NewsORM;
import com.decode.newsreporter.infrastructure.factory.NewsConvertFactory;
import org.springframework.stereotype.Repository;
import java.util.List;

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
    public List<NewsDTO> getAllNews() {
        List<NewsORM> newsList = newsRepositoryORM.findAll();
        return newsConvertFactory.convertNewsFromORM(newsList);
    }

    @Override
    public NewsDTO getNewsById(Long id) {
        NewsORM newsOrm = newsRepositoryORM.findById(id).orElse(null);
        if (newsOrm == null) {
            return null;
        }
        return newsConvertFactory.convertNewsFromORM(newsOrm);
    }

    @Override
    public List<NewsDTO> getNewsById(List<Long> newsIds) {
        List<NewsORM> newsORMList = newsRepositoryORM.findAllById(newsIds);
        return newsConvertFactory.convertNewsFromORM(newsORMList);
    }

    @Override
    public NewsDTO save(NewsDTO newsDTO) {
        NewsORM newsORM = newsConvertFactory.convertNewsFromDTO(newsDTO);
        NewsORM newsORMSaved = newsRepositoryORM.save(newsORM);

        return newsConvertFactory.convertNewsFromORM(newsORMSaved);
    }
}
