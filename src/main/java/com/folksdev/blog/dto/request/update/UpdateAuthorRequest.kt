package com.folksdev.blog.dto.request.update

import com.folksdev.blog.entity.Author
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class UpdateAuthorRequest(

    @field:NotBlank
    val name: String,

    @field:Email
    val email: String,

    val gender: Author.Gender
)
