package com.folksdev.blog.repository;

import com.folksdev.blog.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog,String> {

}
