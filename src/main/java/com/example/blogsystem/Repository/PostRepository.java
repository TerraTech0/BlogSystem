package com.example.blogsystem.Repository;

import com.example.blogsystem.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    Post findPostByPostId(Integer id);

    @Query("select p from Post p where p.user_id=?1")
    List<Post> findPostByUser_id(Integer id);

    Post findPostByTitle(String title);


    @Query("select p from Post p where p.publish_date<?1")
    List<Post> findPostByPublish_dateBefore(Date date);
}
