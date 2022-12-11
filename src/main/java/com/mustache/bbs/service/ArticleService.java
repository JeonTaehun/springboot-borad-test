package com.mustache.bbs.service;

import com.mustache.bbs.domain.dto.ArticleResponse;
import com.mustache.bbs.domain.entity.Article;
import com.mustache.bbs.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public Optional<Article> findById(Long id){
        return articleRepository.findById(id);
    }

    public Article saveArticle(ArticleResponse form){
        return articleRepository.save(form.toEntity());
    }

    public Article update(ArticleResponse articleResponse){
        return articleRepository.save(articleResponse.toEntity());
    }

     public List<Article> getArticleList(){
            return articleRepository.findAll();
        }

    public Optional<Article> onePage(Long id){
        return articleRepository.findById(id);
    }

    public void delete(Long id){
        articleRepository.deleteById(id);
    }
}