package com.example.blogsystem.Service;

import com.example.blogsystem.Api.ApiException;
import com.example.blogsystem.Model.Category;
import com.example.blogsystem.Model.Post;
import com.example.blogsystem.Model.User;
import com.example.blogsystem.Repository.CategoryRepository;
import com.example.blogsystem.Repository.PostRepository;
import com.example.blogsystem.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;


    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    public void addPost(Post post){
        User user = userRepository.findUserByUserId(post.getUser_id());
        Category category = categoryRepository.findCategoriesByCategoryId(post.getCategory_id());
        if (user == null || category == null){
            throw new ApiException("user or category not found!");
        }
        postRepository.save(post);
    }

    public void updatePost(Integer id, Post post){
        Post p = postRepository.findPostByPostId(id);
        if (p == null){
            throw new ApiException("post id not found!");
        }
        p.setTitle(post.getTitle());
        p.setContent(post.getContent());
        p.setCategory_id(post.getCategory_id());
        p.setUser_id(post.getUser_id());
        p.setPublish_date(post.getPublish_date());
        postRepository.save(p);
    }

    public void delete(Integer id){
        Post post = postRepository.findPostByPostId(id);
        if (post == null){
            throw new ApiException("id not found!");
        }
        postRepository.delete(post);
    }

    //find all post by user id
    public List<Post> findAllPostByUserId(Integer id){
        List<Post> post = postRepository.findPostByUser_id(id);
        if (post.isEmpty()){
            throw new ApiException("sorry this user has no posts!");
        }
        return post;
    }
    //find post by title
    public Post findPostByTitle(String title){
        Post post = postRepository.findPostByTitle(title);
        if (post == null){
            throw new ApiException("post not found!");
        }
        return post;
    }

    //find post by publish_date before
    public List<Post> findPostByPublishDateBefore(Date date){
        List<Post> post = postRepository.findPostByPublish_dateBefore(date);
        if (post.isEmpty()){
            throw new ApiException("post not found!");
        }
        return post;
    }

}
