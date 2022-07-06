package com.folksdev.blog.dto.request

import java.time.LocalDateTime
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.PastOrPresent

data class CreateCommentRequest(

    @field:NotBlank
    val content: String,

    @field:NotNull
    @field:PastOrPresent
    val time: LocalDateTime,

    )