package com.decode.newsreporter.infrastructure.repository;

import com.decode.newsreporter.infrastructure.entity.NewsORM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepositoryORM extends JpaRepository<NewsORM, Long> {
}
