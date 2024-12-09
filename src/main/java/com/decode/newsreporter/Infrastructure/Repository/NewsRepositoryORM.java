package com.decode.newsreporter.Infrastructure.Repository;


import com.decode.newsreporter.Infrastructure.Entity.NewsORM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepositoryORM extends JpaRepository<NewsORM, Long> {
}
