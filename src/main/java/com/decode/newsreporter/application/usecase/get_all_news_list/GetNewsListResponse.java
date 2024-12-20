package com.decode.newsreporter.application.usecase.get_all_news_list;

import com.decode.newsreporter.domain.entity.NewsDTO;

import java.util.List;

public record GetNewsListResponse (List<NewsDTO> newsDTOList) {
}
