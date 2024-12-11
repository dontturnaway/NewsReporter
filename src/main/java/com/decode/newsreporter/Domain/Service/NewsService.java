package com.decode.newsreporter.Domain.Service;

import com.decode.newsreporter.Domain.Entity.News;
import com.decode.newsreporter.Domain.Repository.NewsRepository;
import com.decode.newsreporter.Infrastructure.Controller.Exceptions.WrongNewsId;
import com.decode.newsreporter.Infrastructure.Dto.NewsDTO;

import java.util.List;

public interface NewsService {
    List<NewsDTO> getAllNews();
    NewsDTO getNewsById(Long id) throws WrongNewsId; //React in service
    NewsDTO save(News news) throws IllegalArgumentException;
}
