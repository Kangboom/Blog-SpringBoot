package me.limkangbeom.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.limkangbeom.springbootdeveloper.domain.Article;
import me.limkangbeom.springbootdeveloper.dto.AddArticleRequest;
import me.limkangbeom.springbootdeveloper.dto.UpdateArticleRequest;
import me.limkangbeom.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service // 해당 클래스를 '빈'으로 '서블릿 컨테이너'에 등록하는 어노테이션
public class BlogService {
    private final BlogRepository blogRepository;

    // Article - 엔티티 (DB에서 사용되는 객체 == 테이블)
    // AddArticleRequest - DTO (계층끼리 데이터 교환을 위해 사용되는 객체)
    // 블로그 글 추가 메서드
    public Article save(AddArticleRequest request){
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll(){
        return blogRepository.findAll();
    }

    public Article findById(long id){
        return blogRepository.findById(id).orElseThrow(()->new IllegalArgumentException("not found: " + id));
    }

    public void delete(long id){
        blogRepository.deleteById(id);
    }

    @Transactional
    public Article update(long id, UpdateArticleRequest request){
        Article article = blogRepository.findById(id).orElseThrow(()->new IllegalArgumentException("not found: " + id));

        article.update(request.getTitle(), request.getContent());

        return article;
    }
}
