package com.decode.newsreporter.Infrastructure.Service;

import com.decode.newsreporter.Domain.Entity.News;
import com.decode.newsreporter.Domain.Repository.NewsRepository;
import com.decode.newsreporter.Domain.Service.NewsService;
import com.decode.newsreporter.Infrastructure.Controller.Exceptions.WrongNewsId;
import com.decode.newsreporter.Infrastructure.Dto.NewsDTO;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public List<NewsDTO> getAllNews() {
        List <News> newsList = newsRepository.getAllNews();
        List <NewsDTO> newsListDTO = new ArrayList<>();
        newsList.forEach(news ->
                newsListDTO.add(
                    new NewsDTO(news.getId(), news.getDate(), news.getURL().getUrl(), news.getNewsName().getName(), news.getBody()
                    )));
        return newsListDTO;
    }

    @Override
    public NewsDTO getNewsById(Long id) throws WrongNewsId {
        News news = newsRepository.getNewsById(id);
        return new NewsDTO(news.getId(), news.getDate(), news.getURL().getUrl(), news.getNewsName().getName(), news.getBody());
    }

    @Override
    public NewsDTO save(News news) throws IllegalArgumentException {
        News savedNews = newsRepository.save(news);
        return new NewsDTO(savedNews.getId(), savedNews.getDate(), savedNews.getURL().getUrl(), savedNews.getNewsName().getName(), savedNews.getBody());
    }

}
