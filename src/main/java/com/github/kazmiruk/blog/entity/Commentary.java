package com.github.kazmiruk.blog.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

import org.hibernate.annotations.Type;

@Entity
public class Commentary {
    @Id
    @GeneratedValue
    private Integer id;

    @Size(min = 1, message = "You should set commentary text!")
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(length = Integer.MAX_VALUE)
    private String content;

    private Date publishedDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishDate) {
        this.publishedDate = publishDate;
    }
}
