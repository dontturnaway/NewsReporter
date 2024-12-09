package com.decode.newsreporter.Infrastructure.Service;

import com.decode.newsreporter.Domain.Entity.News;
import com.decode.newsreporter.Infrastructure.Entity.NewsDTO;
import java.util.List;

public interface NewsService {
    public List<NewsDTO> getAllNews();
    public NewsDTO getNewsById(Long id);
    public NewsDTO save(News news);
}
