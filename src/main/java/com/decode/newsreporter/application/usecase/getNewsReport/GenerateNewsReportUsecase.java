package com.decode.newsreporter.application.usecase.getNewsReport;

/*

Сценарий 3. Формирование сводного отчёта. В систему передаётся массив из нескольких ID. Система формирует и сохраняет на диск простой HTML-файл со списком примерно такого вида:
В ответ возвращается ссылка на этот файл.

 */

import com.decode.newsreporter.domain.service.NewsService;
import com.decode.newsreporter.domain.service.report_generation.ReportGenerationService;
import com.decode.newsreporter.domain.service.report_generation.ReportLinkRequestDTO;
import com.decode.newsreporter.domain.service.report_generation.ReportLinkResponseDTO;
import com.decode.newsreporter.domain.service.report_generation.UnableToGenerateReportException;
import com.decode.newsreporter.infrastructure.controller.WrongNewsId;
import com.decode.newsreporter.infrastructure.dto.NewsDTO;

import java.util.List;

public class GenerateNewsReportUsecase {
    private final NewsService newsService;
    private final ReportGenerationService reportGenerationService;

    public GenerateNewsReportUsecase(NewsService newsService, ReportGenerationService reportGenerationService) {
        this.newsService = newsService;
        this.reportGenerationService = reportGenerationService;
    }

    public GetNewsReportResponse getReport(GetNewsReportRequest request) throws WrongNewsId, UnableToGenerateReportException {
        List<NewsDTO> newsList = newsService.getNewsByIds(request.newsListIds());
        String requestUrl = request.requestUrl();

        ReportLinkRequestDTO reportLinkRequestDTO = new ReportLinkRequestDTO(newsList, requestUrl);
        ReportLinkResponseDTO reportLinkResponseDTO = reportGenerationService.getFileLink(reportLinkRequestDTO);

        return new GetNewsReportResponse(reportLinkResponseDTO.responseURL());
    }


}