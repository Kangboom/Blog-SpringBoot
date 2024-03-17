package me.limkangbeom.springbootdeveloper.repository;

import me.limkangbeom.springbootdeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}
