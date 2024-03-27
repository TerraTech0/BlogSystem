package com.example.blogsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @NotNull(message = "please enter user id!")
    @Column(columnDefinition = " int not null")
    private Integer user_id;

    @NotNull(message = "please enter post id!")
    @Column(columnDefinition = "int not null")
    private Integer post_id;

    @NotEmpty(message = "please enter a content!")
    @Column(columnDefinition = "varchar(255) not null")
    private String content;

    private Date comment_date;
}
