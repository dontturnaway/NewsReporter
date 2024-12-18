package com.decode.newsreporter.domain.service.report_generation;

public interface ReportGenerationService {
    public ReportLinkResponseDTO getGeneratedReportLink(ReportLinkRequestDTO reportLinkRequestDTO) throws UnableToGenerateReportException;
}
