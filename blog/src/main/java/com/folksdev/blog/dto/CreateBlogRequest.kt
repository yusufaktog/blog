package com.folksdev.blog.dto

import javax.validation.constraints.*

data class CreateBlogRequest(
    @field:NotNull
    @field:NotBlank
    val id: String,
    @field:NotEmpty
    @field:NotBlank
    val name: String,
    @field:NotEmpty
    @field:NotBlank
    val content: String,
    @field:NotEmpty
    @field:NotBlank
    val author: String
)