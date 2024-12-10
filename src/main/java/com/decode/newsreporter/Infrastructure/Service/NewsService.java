package com.decode.newsreporter.Infrastructure.Service;

import com.decode.newsreporter.Domain.Entity.News;
import com.decode.newsreporter.Infrastructure.Controller.Exceptions.WrongNewsId;
import com.decode.newsreporter.Infrastructure.Dto.NewsDTO;
import java.util.List;

public interface NewsService {
    List<NewsDTO> getAllNews();
    NewsDTO getNewsById(Long id) throws WrongNewsId;
    NewsDTO save(News news) throws IllegalArgumentException;
}
