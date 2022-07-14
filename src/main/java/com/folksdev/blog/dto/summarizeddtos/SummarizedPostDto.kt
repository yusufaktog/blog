package com.folksdev.blog.dto.summarizeddtos

import java.time.LocalDate

data class SummarizedPostDto(
    val id: String? = "",
    val content: String,
    val date: LocalDate

)
