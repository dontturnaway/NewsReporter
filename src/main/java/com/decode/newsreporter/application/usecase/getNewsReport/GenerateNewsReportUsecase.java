package com.decode.newsreporter.application.usecase.getNewsReport;

/*

Сценарий 3. Формирование сводного отчёта. В систему передаётся массив из нескольких ID. Система формирует и сохраняет на диск простой HTML-файл со списком примерно такого вида:
В ответ возвращается ссылка на этот файл.

 */

import com.decode.newsreporter.domain.service.NewsService;

public class GenerateNewsReportUsecase {
    private NewsService newsService;

    public GenerateNewsReportUsecase(NewsService newsService) {
        this.newsService = newsService;
    }
}
