package com.folksdev.blog.dto

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDate

data class CommentatorDto(

    val commentator_id: String? = "",
    val commentator_name: String,
    val auth_date: LocalDate,
    val blog_id: String,
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    val comments: List<CommentDto>? = ArrayList(),

)