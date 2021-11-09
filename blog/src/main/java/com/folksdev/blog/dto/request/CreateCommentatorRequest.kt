package com.folksdev.blog.dto.request

import com.folksdev.blog.entity.Blog
import com.folksdev.blog.entity.Comment
import java.time.LocalDate
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.PastOrPresent

data class CreateCommentatorRequest(


    @field:NotBlank
    val commentator_id: String? = "",


    @field:NotBlank
    val commentator_name: String,

    @field:NotNull
    @field:PastOrPresent
    val auth_date: LocalDate,


    @field:NotBlank
    val blog_id: String,

    val comments: Set<Comment>? = HashSet(),

    @field:NotNull
    val blogs: Set<Blog>

)
