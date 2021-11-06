package com.folksdev.blog.repository;

import com.folksdev.blog.entity.Commentator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentatorRepository extends JpaRepository<Commentator,String> {
}
