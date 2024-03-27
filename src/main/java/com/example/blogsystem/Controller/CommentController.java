package com.example.blogsystem.Controller;

import com.example.blogsystem.Model.Comment;
import com.example.blogsystem.Repository.CommentRepository;
import com.example.blogsystem.Service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/get")
    public ResponseEntity getAllComments(){
        return ResponseEntity.ok().body(commentService.getAllComments());
    }

    @PostMapping("/add")
    public ResponseEntity addComment(@RequestBody @Valid Comment comment, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        commentService.addComment(comment);
        return ResponseEntity.ok().body("comment added successfully!");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateComment(@PathVariable Integer id, @RequestBody @Valid Comment comment, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        commentService.updateComment(id, comment);
        return ResponseEntity.ok().body("comment edit successfully!");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteComment(@PathVariable Integer id){
        commentService.deleteComment(id);
        return ResponseEntity.ok().body("comment deleted successfully!");
    }

    @GetMapping("/find-comment-id/{id}")
    public ResponseEntity findCommentByPostId(@PathVariable Integer id){
        return ResponseEntity.ok().body(commentService.findCommentByPostId(id));
    }
}
