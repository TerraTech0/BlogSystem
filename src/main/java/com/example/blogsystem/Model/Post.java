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
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @NotNull(message = "please enter the category id!")
    @Column(columnDefinition = "int not null")
    private Integer category_id;

    @NotEmpty(message = "please enter a title!")
    @Column(columnDefinition = "varchar(15) not null")
    private String title;

    @NotEmpty(message = "please enter a content!")
    @Column(columnDefinition = "varchar(255) not null")
    private String content;

    @NotNull(message = "please enter user id!")
    @Column(columnDefinition = "int not null")
    private Integer user_id;

    private Date publish_date;
}
