package com.decode.newsreporter.domain.entity;

import java.util.Date;

public record NewsDTO (Long id, Date date, String url, String newsName, String body) {
}
