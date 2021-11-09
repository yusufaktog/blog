package com.folksdev.blog.dto

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDate

data class PostDto(
    val post_id: String? = "",
    val post_content: String,
    val post_date: LocalDate,
    val author_id: String,
    val blog_id: String,
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    val comments: List<CommentDto>? = ArrayList()


)