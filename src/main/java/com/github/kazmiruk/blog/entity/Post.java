package com.github.kazmiruk.blog.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;


@Entity
public class Post {
    @Id
    @GeneratedValue
    private Integer id;

    @Size(min = 1, message = "Title should be filled")
    private String title;

    @Size(min = 1, message = "Content should be filled")
    private String content;

    private Date publishedDate;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Commentary> commentaries;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Commentary> getCommentaries() {
        return commentaries;
    }

    public void setCommentaries(List<Commentary> commentaries) {
        this.commentaries = commentaries;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishDate) {
        this.publishedDate = publishDate;
    }
}
