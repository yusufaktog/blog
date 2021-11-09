package com.folksdev.blog.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.folksdev.blog.entity.Author
import com.folksdev.blog.entity.Blog
import com.folksdev.blog.entity.Post
import java.time.LocalDate
import java.time.LocalDateTime

data class AuthorDto(
    val author_id: String? = "",
    val name: String,
    val email: String,
    val dateOfBirth: LocalDate?,
    val gender: Author.Gender,
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    val posts: List<PostDto>? = ArrayList(),
    /*val blogs: List<BlogDto>?,
    val blog_id: String*/

)
