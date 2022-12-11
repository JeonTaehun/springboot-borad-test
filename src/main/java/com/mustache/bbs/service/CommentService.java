package com.mustache.bbs.service;

import com.mustache.bbs.domain.dto.CommentRequest;
import com.mustache.bbs.domain.entity.Article;
import com.mustache.bbs.domain.entity.Comment;
import com.mustache.bbs.repository.ArticleRepository;
import com.mustache.bbs.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    public void createComment(CommentRequest dto) {

        // 글이 없을때 댓글 달기 불가
        Article article = articleRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("글이 없습니다."));

        Comment comment = Comment.builder()
                .id(dto.getId())
                .content(dto.getContent())
                .commentUserName(dto.getCommentUserName())
                .article(article)
                .build();

        commentRepository.save(comment);

    }

}