package com.decode.newsreporter.application.report_generation;

import com.decode.newsreporter.infrastructure.entity.NewsDTO;

import java.util.List;

public record ReportLinkRequestDTO(List<NewsDTO> newsList) {
}
