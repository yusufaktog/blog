package com.folksdev.blog.dto.summarizeddtos

import java.time.LocalDate

data class SummarizedPostDto(
    val post_id: String? = "",
    val post_content: String,
    val post_date: LocalDate

)
