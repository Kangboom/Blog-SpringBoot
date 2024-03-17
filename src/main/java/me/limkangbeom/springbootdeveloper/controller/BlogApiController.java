package me.limkangbeom.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.limkangbeom.springbootdeveloper.domain.Article;
import me.limkangbeom.springbootdeveloper.dto.AddArticleRequest;
import me.limkangbeom.springbootdeveloper.dto.ArticleResponse;
import me.limkangbeom.springbootdeveloper.dto.UpdateArticleRequest;
import me.limkangbeom.springbootdeveloper.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BlogApiController {

    private final BlogService blogService;

    @PostMapping("/api/articles")
    // @RequestBody는 HTTP 요청에 해당하는 값들을 @RequestBody 어노테이션이 붙은 객체에 매핑해서 받아 올 수 있도록 한다.
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request){
        Article savedArticle = blogService.save(request);
        // Http 상태코드 201, Created를 응답하고 테이블에 저장된 객체를 반환한다.
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles(){
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok().body(articles);
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id){
        Article article = blogService.findById(id);

        return ResponseEntity.ok().body(new ArticleResponse(article));
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id){
        blogService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id, @RequestBody UpdateArticleRequest request){
        Article updatedArticle = blogService.update(id, request);

        return ResponseEntity.ok().body(updatedArticle);
    }
}
