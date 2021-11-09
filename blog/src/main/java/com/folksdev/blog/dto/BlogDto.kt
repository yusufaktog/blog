package com.folksdev.blog.dto

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDate

data class BlogDto(
    val blog_id: String? = "",
    val blog_name: String,
    val creation_date: LocalDate,
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    val authors: List<AuthorDto>? = ArrayList()
    /*
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    val posts: List<PostDto>? = ArrayList(),
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    val commentators: List<CommentatorDto>?= ArrayList()*/
)
