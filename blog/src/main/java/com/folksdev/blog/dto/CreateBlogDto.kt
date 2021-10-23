package com.folksdev.blog.dto

data class CreateBlogDto(
    val id: String,
    val name: String,
    val content: String,
    val author: String,
)
