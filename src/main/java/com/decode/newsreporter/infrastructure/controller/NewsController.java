package com.decode.newsreporter.infrastructure.controller;

import com.decode.newsreporter.application.gateway.CanGetRemoteDataFromURLException;
import com.decode.newsreporter.application.usecase.get_all_news_list.GetAllNewsListUsecase;
import com.decode.newsreporter.application.usecase.get_news_by_id.GetNewsByIDRequest;
import com.decode.newsreporter.application.usecase.get_news_by_id.GetNewsByIDUsecase;
import com.decode.newsreporter.application.usecase.get_news_report.GenerateNewsReportUsecase;
import com.decode.newsreporter.application.usecase.get_news_report.GetNewsReportRequest;
import com.decode.newsreporter.application.usecase.get_news_report.GetNewsReportResponse;
import com.decode.newsreporter.application.usecase.submit_news.SubmitNewsRequestDTO;
import com.decode.newsreporter.application.usecase.submit_news.SubmitNewsResponseDTO;
import com.decode.newsreporter.application.usecase.submit_news.SubmitNewsUsecase;
import com.decode.newsreporter.infrastructure.service.news_parser.parsing_strategy.CantParseNewsException;
import com.decode.newsreporter.infrastructure.service.WrongUrlProvided;
import com.decode.newsreporter.application.service.report_generation.UnableToGenerateReportException;
import com.decode.newsreporter.infrastructure.entity.NewsDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/news")
public class NewsController {

    private final SubmitNewsUsecase submitNewsUsecase;
    private final GenerateNewsReportUsecase generateNewsReportUsecase;
    private final GetAllNewsListUsecase getAllNewsListUsecase;
    private final GetNewsByIDUsecase getNewsByIDUsecase;

    public NewsController(SubmitNewsUsecase submitNewsUsecase,
                          GenerateNewsReportUsecase generateNewsReportUsecase,
                          GetAllNewsListUsecase getAllNewsListUsecase,
                          GetNewsByIDUsecase getNewsByIDUsecase
    ) {
        this.submitNewsUsecase = submitNewsUsecase;
        this.generateNewsReportUsecase = generateNewsReportUsecase;
        this.getAllNewsListUsecase = getAllNewsListUsecase;
        this.getNewsByIDUsecase = getNewsByIDUsecase;
    }

    @GetMapping()
    public List<NewsDTO> getAllNews() {
        return getAllNewsListUsecase.getNewsList().newsDTOList();
    }

    @GetMapping("/{newsId}")
    public NewsDTO getNewsById(GetNewsByIDRequest getNewsByIDRequest) throws WrongNewsIdProvided {
        if (getNewsByIDRequest == null || getNewsByIDRequest.newsId() == null) {
            throw new WrongNewsIdProvided();
        }
        return getNewsByIDUsecase.getNewsList(getNewsByIDRequest).newsDTO();
    }

    @PostMapping()
    public SubmitNewsResponseDTO postNews(@RequestBody @Valid @NotNull SubmitNewsRequestDTO submitNewsRequestDTO) throws
                                                                        CantParseNewsException,
                                                                        CanGetRemoteDataFromURLException,
                                                                        WrongUrlProvided {
        if (submitNewsRequestDTO.URL() ==null ||  submitNewsRequestDTO.URL().isEmpty())
            throw new WrongUrlProvided();

        return submitNewsUsecase.submitNews(submitNewsRequestDTO);
    }

    @PostMapping( "/report")
    public GetNewsReportResponse generateNewsReport(@RequestBody @Valid @NotNull GetNewsReportRequest getNewsReportRequest) throws
                                            WrongNewsIdProvided,
                                            UnableToGenerateReportException {

        if (getNewsReportRequest ==null || getNewsReportRequest.newsListIds().isEmpty())
            throw new WrongNewsIdProvided();

        return generateNewsReportUsecase.getReport(getNewsReportRequest);
    }

}
