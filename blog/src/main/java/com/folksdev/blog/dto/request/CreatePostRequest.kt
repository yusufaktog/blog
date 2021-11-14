package com.folksdev.blog.dto.request

import com.folksdev.blog.entity.Author
import com.folksdev.blog.entity.Blog
import java.time.LocalDate
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.PastOrPresent

data class CreatePostRequest(

    @field:NotBlank
    val post_content: String,

    @field:NotNull
    @field:PastOrPresent
    val post_date: LocalDate,

    @field:NotNull
    val author: Author,

    @field:NotNull
    val blog: Blog
)
