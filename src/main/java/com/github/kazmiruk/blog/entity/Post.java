package com.github.kazmiruk.blog.entity;

import org.hibernate.annotations.Type;

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
    @Column(length = 1000)
    private String title;

    @Column(length = 255)
    private String previewImg;

    @Size(min = 1, message = "Content should be filled")
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(length = Integer.MAX_VALUE)
    private String content;

    private Date publishedDate;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Commentary> commentaries;

    @ManyToMany
    @JoinTable
    private List<Tag> tags;

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

    public String getPreviewImg() {
        return previewImg;
    }

    public void setPreviewImg(String previewImg) {
        this.previewImg = previewImg;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
