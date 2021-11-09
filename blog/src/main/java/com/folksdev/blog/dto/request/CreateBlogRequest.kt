package com.folksdev.blog.dto.request

import com.folksdev.blog.entity.Author
import com.folksdev.blog.entity.Commentator
import com.folksdev.blog.entity.Post
import java.time.LocalDate
import javax.validation.constraints.*

data class CreateBlogRequest(


    @field:NotBlank
    val blog_id: String? = "",


    @field:NotBlank
    val blog_name: String,

    @field:NotNull
    @field:PastOrPresent
    val creation_date: LocalDate,

    val authors: Set<Author>? = HashSet(),
    val posts: Set<Post>? = HashSet(),
    val commentators: Set<Commentator>? = HashSet()

)