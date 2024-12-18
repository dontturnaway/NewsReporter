package com.decode.newsreporter.application.service.report_generation;

public interface ReportGenerationService {
    public ReportLinkResponseDTO getGeneratedReportLink(ReportLinkRequestDTO reportLinkRequestDTO) throws UnableToGenerateReportException;
}
