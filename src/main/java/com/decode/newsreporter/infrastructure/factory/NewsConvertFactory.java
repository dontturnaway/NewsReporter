package com.decode.newsreporter.infrastructure.factory;

import com.decode.newsreporter.domain.entity.NewsDTO;
import com.decode.newsreporter.infrastructure.entity.NewsORM;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NewsConvertFactory {

    public NewsDTO convertNewsFromORM(NewsORM newsORM) {
            return new NewsDTO(newsORM.getId(),
                    newsORM.getDate(),
                    newsORM.getURL(),
                    newsORM.getName(),
                    newsORM.getBody());
    }

    public List<NewsDTO> convertNewsFromORM(List<NewsORM> newsORM) {
        List<NewsDTO> newsList = new ArrayList<>();
        newsORM.forEach(newsOrmItem -> {
                newsList.add(new NewsDTO(newsOrmItem.getId(),
                        newsOrmItem.getDate(),
                        newsOrmItem.getURL(),
                        newsOrmItem.getName(),
                        newsOrmItem.getBody())
                );
        });
        return newsList;
    }

    public NewsORM convertNewsFromDTO(NewsDTO newsDTO) {
        return new NewsORM(newsDTO.id(),
                newsDTO.date(),
                newsDTO.url(),
                newsDTO.newsName(),
                newsDTO.body());
    }

}
