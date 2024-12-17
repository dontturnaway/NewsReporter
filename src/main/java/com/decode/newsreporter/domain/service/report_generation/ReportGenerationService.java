package com.decode.newsreporter.domain.service.report_generation;

public interface ReportGenerationService {
    public ReportLinkResponseDTO getFileLink(ReportLinkRequestDTO reportLinkRequestDTO) throws UnableToGenerateReportException;
}
