package com.example.blogsystem.Controller;

import com.example.blogsystem.Model.Post;
import com.example.blogsystem.Service.CategoryService;
import com.example.blogsystem.Service.PostService;
import com.example.blogsystem.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;


    @GetMapping("/get")
    public ResponseEntity getAllPosts(){
        return ResponseEntity.ok().body(postService.getAllPosts());
    }

    @PostMapping("/add")
    public ResponseEntity addPost(@RequestBody @Valid Post post, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        postService.addPost(post);
        return ResponseEntity.ok().body("post added successfully!");
    }

    @PostMapping("/update/{id}")
    public ResponseEntity updatePost(@PathVariable Integer id, @RequestBody Post post, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        postService.updatePost(id, post);
        return ResponseEntity.ok().body("post updated successfully!");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePost(@PathVariable Integer id){
        postService.delete(id);
        return ResponseEntity.ok().body("post deleted successfully!");
    }

    @GetMapping("/get-post-title/{title}")
    public ResponseEntity findPostByTitle(@PathVariable String title){
        return ResponseEntity.ok().body(postService.findPostByTitle(title));
    }

    @GetMapping("/get-post-date-before/{date}")
    public ResponseEntity findPostByPublishDateBefore(@PathVariable Date date){
        return ResponseEntity.ok().body(postService.findPostByPublishDateBefore(date));
    }

    @GetMapping("/get-post-id/{id}")
    public ResponseEntity findAllPostByUserId(@PathVariable Integer id){
        return ResponseEntity.ok().body(postService.findAllPostByUserId(id));
    }
}
