package com.decode.newsreporter.Infrastructure.Controller;

import com.decode.newsreporter.Application.UseCase.Gateway.CanGetRemoteDataFromURLException;
import com.decode.newsreporter.Domain.Service.ParsingStrategy.CantParseNewsException;
import com.decode.newsreporter.Domain.Service.ParsingStrategy.WrongUrlProvided;
import com.decode.newsreporter.Infrastructure.Controller.Exceptions.WrongNewsId;
import com.decode.newsreporter.Infrastructure.Service.UnableToGenerateReportException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CanGetRemoteDataFromURLException.class)
    public ResponseEntity<String> handleCanGetRemoteDataFromURLException() {
        return new ResponseEntity<>("Can't fetch data from remote url", HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CantParseNewsException.class)
    public ResponseEntity<String> handleCantParseNewsException() {
        return new ResponseEntity<>("Can't parse news from url", HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WrongUrlProvided.class)
    public ResponseEntity<String> handleIncorrectUrlProvidedForParsingException() {
        return new ResponseEntity<>("Incorrect URL provided for parsing", HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WrongNewsId.class)
    public ResponseEntity<String> handleWrongNewsIdException() {
        return new ResponseEntity<>("Wrong news id provided", HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UnableToGenerateReportException.class)
    public ResponseEntity<String> handleUnableToGenerateReportExceptionException() {
        return new ResponseEntity<>("Unable to generate report", HttpStatus.BAD_REQUEST);
    }

}