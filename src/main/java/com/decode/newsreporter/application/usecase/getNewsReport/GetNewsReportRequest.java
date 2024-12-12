package com.decode.newsreporter.application.usecase.getNewsReport;

import java.util.List;

public record GetNewsReportRequest(List<Long> newsList) {
}
