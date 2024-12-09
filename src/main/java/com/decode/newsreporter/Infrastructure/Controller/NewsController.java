package com.decode.newsreporter.Infrastructure.Controller;

import com.decode.newsreporter.Application.UseCase.Gateway.CanGetRemoteDataFromURLException;
import com.decode.newsreporter.Application.UseCase.SubmitNews.SubmitNewsRequest;
import com.decode.newsreporter.Application.UseCase.SubmitNews.SubmitNewsResponse;
import com.decode.newsreporter.Application.UseCase.SubmitNews.SubmitNewsUsecase;
import com.decode.newsreporter.Domain.Entity.News;
import com.decode.newsreporter.Domain.Repository.NewsRepositoryInterface;
import com.decode.newsreporter.Domain.Service.ParsingStrategy.CantParseNewsException;
import com.decode.newsreporter.Domain.Service.ParsingStrategy.IncorrectUrlProvidedForParsing;
import com.decode.newsreporter.Infrastructure.Command.SubmitNewsCommand;
import com.decode.newsreporter.Infrastructure.Controller.Exceptions.EmptyNewsIdProvided;
import com.decode.newsreporter.Infrastructure.Controller.Exceptions.EmptyURLProvided;
import com.decode.newsreporter.Infrastructure.Controller.Exceptions.WrongNewsId;
import com.decode.newsreporter.Infrastructure.Entity.NewsDTO;
import com.decode.newsreporter.Infrastructure.Service.NewsServiceImpl;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class NewsController {

    private final NewsServiceImpl newsService;
    private final SubmitNewsCommand submitNewsCommand;
    private static final String BASE_NEWS_URL="/api/v1/news";

    public NewsController(SubmitNewsCommand submitNewsCommand, NewsServiceImpl newsService) {
        this.submitNewsCommand = submitNewsCommand;
        this.newsService = newsService;
    }

    @GetMapping(value=BASE_NEWS_URL, produces = APPLICATION_JSON_VALUE)
    public List<NewsDTO> getAllNews() {
        return newsService.getAllNews();
    }

    @GetMapping(value=BASE_NEWS_URL + "/{newsId}", produces = APPLICATION_JSON_VALUE)
    public NewsDTO getNewsById(@PathVariable Long newsId) throws EmptyNewsIdProvided {
        if (newsId == null)
            throw new EmptyNewsIdProvided();
        NewsDTO newsDTO =  newsService.getNewsById(newsId);
        if (newsDTO == null) {
            throw new WrongNewsId();
        }
        return newsDTO;
    }

    @PostMapping(value = BASE_NEWS_URL, consumes = APPLICATION_JSON_VALUE)
    public SubmitNewsResponse postNews(@RequestBody SubmitNewsRequest submitNewsRequest) throws
                                                                        CantParseNewsException,
                                                                        CanGetRemoteDataFromURLException,
                                                                        IncorrectUrlProvidedForParsing,
                                                                        EmptyURLProvided {

        if (submitNewsRequest.URL() ==null ||  submitNewsRequest.URL().isEmpty())
            throw new EmptyURLProvided();

        return submitNewsCommand.execute(submitNewsRequest);
    }
}
