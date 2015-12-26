package com.github.kazmiruk.blog.service;


import com.github.kazmiruk.blog.entity.Tag;
import com.github.kazmiruk.blog.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    public Tag findOne(int id) {
        return tagRepository.findOne(id);
    }
}
