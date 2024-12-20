/* Сценарий 3. Формирование сводного отчёта. В систему передаётся массив из нескольких ID. Система формирует и сохраняет на диск простой HTML-файл со списком примерно такого вида:
В ответ возвращается ссылка на этот файл. */

package com.decode.newsreporter.application.usecase.get_news_report;

import com.decode.newsreporter.domain.repository.NewsRepository;
import com.decode.newsreporter.application.report_generation.ReportGeneration;
import com.decode.newsreporter.application.report_generation.ReportLinkRequestDTO;
import com.decode.newsreporter.application.report_generation.ReportLinkResponseDTO;
import com.decode.newsreporter.application.report_generation.UnableToGenerateReportException;
import com.decode.newsreporter.infrastructure.controller.WrongNewsIdProvided;
import com.decode.newsreporter.domain.entity.NewsDTO;

import java.util.List;

public class GenerateNewsReportUsecase {
    private final NewsRepository newsRepository;
    private final ReportGeneration reportGeneration;

    public GenerateNewsReportUsecase(NewsRepository newsRepository, ReportGeneration reportGeneration) {
        this.newsRepository = newsRepository;
        this.reportGeneration = reportGeneration;
    }

    public GetNewsReportResponse getReport(GetNewsReportRequest request) throws WrongNewsIdProvided, UnableToGenerateReportException {
        List<NewsDTO> newsList = newsRepository.getNewsById(request.newsListIds()); //change to repo
        ReportLinkRequestDTO reportLinkRequestDTO = new ReportLinkRequestDTO(newsList);
        ReportLinkResponseDTO reportLinkResponseDTO = reportGeneration.getGeneratedReportLink(reportLinkRequestDTO);

        return new GetNewsReportResponse(reportLinkResponseDTO.responseURL());
    }


}
