package com.decode.newsreporter.application.usecase.get_news_by_id;

import com.decode.newsreporter.infrastructure.entity.NewsDTO;

public record GetNewsByIdResponse(NewsDTO newsDTO) {
}
