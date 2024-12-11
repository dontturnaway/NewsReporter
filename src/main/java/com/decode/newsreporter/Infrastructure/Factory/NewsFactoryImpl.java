package com.decode.newsreporter.Infrastructure.Factory;
import com.decode.newsreporter.Application.UseCase.Gateway.NewsGatewayResponseDTO;
import com.decode.newsreporter.Domain.Entity.News;
import com.decode.newsreporter.Domain.Factory.NewsFactory;
import com.decode.newsreporter.Domain.Service.NewsParserResponseDTO;
import com.decode.newsreporter.Domain.ValueObject.NewsName;
import com.decode.newsreporter.Domain.ValueObject.URL;
import org.springframework.stereotype.Component;

@Component
public class NewsFactoryImpl implements NewsFactory {

    @Override
    public News createNews(Long id, URL url, NewsName newsName, String body) {
        return new News(id, url, newsName, body);
    }
}
