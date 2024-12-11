package com.decode.newsreporter.Domain.Factory;

import com.decode.newsreporter.Application.UseCase.Gateway.NewsGatewayResponseDTO;
import com.decode.newsreporter.Domain.Entity.News;
import com.decode.newsreporter.Domain.Service.NewsParserResponseDTO;
import com.decode.newsreporter.Domain.ValueObject.NewsName;
import com.decode.newsreporter.Domain.ValueObject.URL;

public interface NewsFactory {
    News createNews(Long id, URL url, NewsName newsName, String body);
}
