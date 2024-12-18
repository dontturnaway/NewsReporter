package com.decode.newsreporter.application.usecase.get_news_list;

import com.decode.newsreporter.infrastructure.dto.NewsDTO;

import java.util.List;

public record GetNewsListResponse (List<NewsDTO> newsDTOList) {
}
