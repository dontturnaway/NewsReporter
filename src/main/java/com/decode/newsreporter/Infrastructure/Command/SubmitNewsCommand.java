package com.decode.newsreporter.Infrastructure.Command;


import com.decode.newsreporter.Application.UseCase.Gateway.CanGetRemoteDataFromURLException;
import com.decode.newsreporter.Application.UseCase.SubmitNews.SubmitNewsRequest;
import com.decode.newsreporter.Application.UseCase.SubmitNews.SubmitNewsResponse;
import com.decode.newsreporter.Application.UseCase.SubmitNews.SubmitNewsUsecase;
import com.decode.newsreporter.Domain.Service.ParsingStrategy.CantParseNewsException;
import com.decode.newsreporter.Domain.Service.ParsingStrategy.WrongUrlProvided;
import org.springframework.stereotype.Component;


import java.util.Objects;

@Component
public class SubmitNewsCommand {

    private final SubmitNewsUsecase submitNewsUsecase;

    public SubmitNewsCommand(SubmitNewsUsecase submitNewsUsecase) {
        this.submitNewsUsecase = submitNewsUsecase;
    }

    public SubmitNewsResponse execute(SubmitNewsRequest request) throws CanGetRemoteDataFromURLException,
                                                                        CantParseNewsException,
                                                                        WrongUrlProvided {
        Objects.requireNonNull(request, "Request must not be null");
        return submitNewsUsecase.submitNews(request);
    }

}