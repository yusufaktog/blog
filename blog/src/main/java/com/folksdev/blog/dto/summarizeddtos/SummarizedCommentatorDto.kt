package com.folksdev.blog.dto.summarizeddtos

import java.time.LocalDate

data class SummarizedCommentatorDto(
    val id: String? = "",
    val name: String,
    val authDate: LocalDate

)
