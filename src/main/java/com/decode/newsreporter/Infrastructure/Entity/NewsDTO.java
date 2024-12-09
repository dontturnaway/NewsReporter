package com.decode.newsreporter.Infrastructure.Entity;

import java.util.Date;

public record NewsDTO (Long id, Date date, String URL, String name, String body) {
}
