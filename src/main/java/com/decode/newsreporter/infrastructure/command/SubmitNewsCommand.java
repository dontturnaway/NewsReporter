package com.decode.newsreporter.infrastructure.command;

import com.decode.newsreporter.application.gateway.CanGetRemoteDataFromURLException;
import com.decode.newsreporter.application.usecase.submit_news.SubmitNewsRequestDTO;
import com.decode.newsreporter.application.usecase.submit_news.SubmitNewsResponseDTO;
import com.decode.newsreporter.application.usecase.submit_news.SubmitNewsUsecase;
import com.decode.newsreporter.domain.service.parsing_strategy.CantParseNewsException;
import com.decode.newsreporter.domain.service.parsing_strategy.WrongUrlProvided;
import org.springframework.stereotype.Component;


import java.util.Objects;

@Component
public class SubmitNewsCommand {

    private final SubmitNewsUsecase submitNewsUsecase;

    public SubmitNewsCommand(SubmitNewsUsecase submitNewsUsecase) {
        this.submitNewsUsecase = submitNewsUsecase;
    }

    public SubmitNewsResponseDTO execute(SubmitNewsRequestDTO request) throws CanGetRemoteDataFromURLException,
                                                                        CantParseNewsException,
                                                                        WrongUrlProvided {
        Objects.requireNonNull(request, "Request must not be null");
        return submitNewsUsecase.submitNews(request);
    }

}
