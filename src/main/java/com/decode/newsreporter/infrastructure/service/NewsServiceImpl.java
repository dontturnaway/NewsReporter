package com.decode.newsreporter.infrastructure.service;

import com.decode.newsreporter.domain.entity.News;
import com.decode.newsreporter.domain.repository.NewsRepository;
import com.decode.newsreporter.domain.service.NewsService;
import com.decode.newsreporter.infrastructure.controller.WrongNewsIdProvided;
import com.decode.newsreporter.infrastructure.entity.NewsDTO;
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
    public NewsDTO getNewsById(Long id) throws WrongNewsIdProvided {
        News news = newsRepository.getNewsById(id);
        if (news == null) {
            throw new WrongNewsIdProvided();
        }
        return new NewsDTO(news.getId(), news.getDate(), news.getURL().getUrl(), news.getNewsName().getName(), news.getBody());
    }

    @Override
    public List<NewsDTO> getNewsById(List<Long> ids) throws WrongNewsIdProvided {
        List<News> newsList = newsRepository.getNewsById(ids);
        if (newsList == null) {
            throw new WrongNewsIdProvided();
        }
        List<NewsDTO> newsListDTOList = new ArrayList<>();
        newsList.forEach(news->
                newsListDTOList.add(new NewsDTO(news.getId(), news.getDate(), news.getURL().getUrl(), news.getNewsName().getName(), news.getBody()))
        );
        return newsListDTOList;
    }

    @Override
    public NewsDTO save(News news) throws IllegalArgumentException {
        News savedNews = newsRepository.save(news);
        return new NewsDTO(savedNews.getId(), savedNews.getDate(), savedNews.getURL().getUrl(), savedNews.getNewsName().getName(), savedNews.getBody());
    }

}
