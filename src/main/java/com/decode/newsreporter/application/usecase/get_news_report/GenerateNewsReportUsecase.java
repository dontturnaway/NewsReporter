/* Сценарий 3. Формирование сводного отчёта. В систему передаётся массив из нескольких ID. Система формирует и сохраняет на диск простой HTML-файл со списком примерно такого вида:
В ответ возвращается ссылка на этот файл. */

package com.decode.newsreporter.application.usecase.get_news_report;

import com.decode.newsreporter.domain.service.NewsService;
import com.decode.newsreporter.application.service.report_generation.ReportGenerationService;
import com.decode.newsreporter.application.service.report_generation.ReportLinkRequestDTO;
import com.decode.newsreporter.application.service.report_generation.ReportLinkResponseDTO;
import com.decode.newsreporter.application.service.report_generation.UnableToGenerateReportException;
import com.decode.newsreporter.infrastructure.controller.WrongNewsIdProvided;
import com.decode.newsreporter.infrastructure.entity.NewsDTO;

import java.util.List;

public class GenerateNewsReportUsecase {
    private final NewsService newsService;
    private final ReportGenerationService reportGenerationService;

    public GenerateNewsReportUsecase(NewsService newsService, ReportGenerationService reportGenerationService) {
        this.newsService = newsService;
        this.reportGenerationService = reportGenerationService;
    }

    public GetNewsReportResponse getReport(GetNewsReportRequest request) throws WrongNewsIdProvided, UnableToGenerateReportException {
        List<NewsDTO> newsList = newsService.getNewsById(request.newsListIds());
        ReportLinkRequestDTO reportLinkRequestDTO = new ReportLinkRequestDTO(newsList);
        ReportLinkResponseDTO reportLinkResponseDTO = reportGenerationService.getGeneratedReportLink(reportLinkRequestDTO);

        return new GetNewsReportResponse(reportLinkResponseDTO.responseURL());
    }


}
