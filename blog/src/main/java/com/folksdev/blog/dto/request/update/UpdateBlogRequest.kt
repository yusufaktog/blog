package com.folksdev.blog.dto.request.update

import javax.validation.constraints.NotBlank

data class UpdateBlogRequest(
    @field:NotBlank
    val blog_name: String
)
