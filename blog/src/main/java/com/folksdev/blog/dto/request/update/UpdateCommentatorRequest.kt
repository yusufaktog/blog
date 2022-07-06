package com.folksdev.blog.dto.request.update

import javax.validation.constraints.NotBlank

data class UpdateCommentatorRequest(
    @field:NotBlank
    val name: String
)
