package com.folksdev.blog.dto.request

import com.folksdev.blog.entity.Author
import com.folksdev.blog.entity.Blog

import com.folksdev.blog.entity.Post
import java.time.LocalDate
import java.time.LocalDateTime
import javax.validation.constraints.*

data class CreateAuthorRequest(

    @field:NotBlank
    val author_id: String? = "",


    @field:NotBlank
    val name: String,

    @field:Email
    val email: String,

    @field: PastOrPresent
    val dateOfBirth: LocalDate,

    val gender: Author.Gender,

    @field: PastOrPresent
    val auth_date : LocalDateTime,


    @field:NotBlank
    val blog_id : String,

    val posts : Set<Post>? = HashSet(),


    @field:NotEmpty
    val blogs: Set<Blog>?= HashSet()


    )
