package com.decode.newsreporter.application.report_generation;

public interface ReportGenerationService {
    ReportLinkResponseDTO getGeneratedReportLink(ReportLinkRequestDTO reportLinkRequestDTO) throws UnableToGenerateReportException;
}
