package com.mustache.bbs.controller;


import com.mustache.bbs.domain.dto.ArticleResponse;
import com.mustache.bbs.domain.entity.Article;
import com.mustache.bbs.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/articles")
@Slf4j // 로깅을 위한 어노테이션 log를 사용할 수있다.
public class ArticleController {

    private final ArticleService articleService;

    //메인페이지
    @GetMapping("")
    public String index() {
        return "redirect:/articles/list";
    }

    //생성
    @GetMapping(value = "/new")
    public String newArticleForm() {
        return "articles/new";
    }

    //글 목록
    @GetMapping(value = "/list")
    public String list(Model model) {
        model.addAttribute("articles", articleService.getArticleList());
        return "list";
    }

    //상세페이지
    @GetMapping(value = "/{id}")
    public String selectSingle(@PathVariable Long id, Model model) {
        Optional<Article> optArticle = articleService.onePage(id);
        log.info(String.valueOf(optArticle));

        if(optArticle.isPresent()){
            model.addAttribute("article", optArticle.get());
            return "show";
        }else{
            return "error";
        }
    }

    //생성
    @PostMapping(value = "/posts")
    public String createArticle(ArticleResponse form) {
        log.info(form.getTitle());
        Article savedArticle = articleService.saveArticle(form);
        log.info("generatedId:{}", savedArticle.getId());
        return String.format("redirect:/articles/%d", savedArticle.getId());
    }

    //수정
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Optional<Article> optionalArticle = articleService.findById(id);
        if (optionalArticle.isPresent()) {
            model.addAttribute("article", optionalArticle.get());
            return "edit";
        } else {
            model.addAttribute("message", String.format("%d가 없습니다.", id));
            return "error";
        }
    }

    //수정
    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id, ArticleResponse articleResponse, Model model) {
        log.info("title:{} content:{}", articleResponse.getTitle(), articleResponse.getContent());
        Article article = articleService.update(articleResponse);
        model.addAttribute("article", article);
        return String.format("redirect:/articles/%d", article.getId());
    }

    //삭제
    @GetMapping("/{id}/delete")
    public String edit(@PathVariable Long id) {
        articleService.delete(id);
        return "redirect:/articles";
    }

}