package com.decode.newsreporter.application.report_generation;

public interface ReportGeneration {
    ReportLinkResponseDTO getGeneratedReportLink(ReportLinkRequestDTO reportLinkRequestDTO) throws UnableToGenerateReportException;
}
