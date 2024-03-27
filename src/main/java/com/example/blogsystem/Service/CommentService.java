package com.example.blogsystem.Service;

import com.example.blogsystem.Api.ApiException;
import com.example.blogsystem.Model.Comment;
import com.example.blogsystem.Model.Post;
import com.example.blogsystem.Model.User;
import com.example.blogsystem.Repository.CommentRepository;
import com.example.blogsystem.Repository.PostRepository;
import com.example.blogsystem.Repository.UserRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public List<Comment> getAllComments(){
        return commentRepository.findAll();
    }

    public void addComment(Comment comment){
        User user = userRepository.findUserByUserId(comment.getUser_id());
        Post post = postRepository.findPostByPostId(comment.getPost_id());
        if (user == null || post == null){
            throw new ApiException("user or post not found!!!");
        }
        commentRepository.save(comment);
    }

    public void updateComment(Integer id, Comment comment){
        Comment c = commentRepository.findCommentByCommentId(id);
        if (c == null){
            throw new ApiException("comment not found!");
        }
        c.setContent(comment.getContent());
        c.setPost_id(comment.getPost_id());
        c.setUser_id(comment.getUser_id());
        c.setComment_date(comment.getComment_date());
        commentRepository.save(c);
    }

    public void deleteComment(Integer id){
        Comment comment = commentRepository.findCommentByCommentId(id);
        if (comment == null){
            throw new ApiException("comment not found!");
        }
        commentRepository.delete(comment);
    }

    //find comment by post id
    public Comment findCommentByPostId(Integer id){
        Comment comment = commentRepository.findCommentByCommentId(id);
        if (comment == null){
            throw new ApiException("comment not found!");
        }
        return comment;
    }
}
