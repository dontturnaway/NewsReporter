package com.decode.newsreporter.application.usecase.get_news_report;

import java.util.List;

public record GetNewsReportRequest(List<Long> newsListIds, String requestUrl) {
}
