package com.folksdev.blog.dto.summarizeddtos

import java.time.LocalDate

data class SummarizedBlogDto(
    val id: String? = "",
    val name: String,
    val creationDate: LocalDate

)