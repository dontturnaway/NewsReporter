package com.decode.newsreporter.infrastructure.entity;

import java.util.Date;

public record NewsDTO (Long id, Date date, String URL, String name, String body) {
}
