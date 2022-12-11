package com.mustache.bbs.controller;

import com.mustache.bbs.domain.dto.CommentRequest;
import com.mustache.bbs.domain.entity.Article;
import com.mustache.bbs.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/commnet")
@Slf4j // 로깅을 위한 어노테이션 log를 사용할 수있다.
public class CommentController {

    private final CommentService commentService;


    @PostMapping
    public ResponseEntity<String> createComment(@RequestBody CommentRequest commentRequest){
        commentService.createComment(commentRequest);
        return ResponseEntity.ok().body("댓글이 등록 되었습니다.");

    }




}