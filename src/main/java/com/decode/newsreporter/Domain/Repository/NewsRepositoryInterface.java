package com.decode.newsreporter.Domain.Repository;
import com.decode.newsreporter.Domain.Entity.News;
import com.decode.newsreporter.Infrastructure.Controller.Exceptions.WrongNewsId;


import java.util.List;

public interface NewsRepositoryInterface {
    List<News> getAllNews();
    News getNewsById(Long id) throws WrongNewsId;
    News save(News news) throws IllegalArgumentException;
}
