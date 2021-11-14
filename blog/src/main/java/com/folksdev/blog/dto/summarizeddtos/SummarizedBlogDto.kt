package com.folksdev.blog.dto.summarizeddtos

import java.time.LocalDate

data class SummarizedBlogDto(
    val blog_id: String? = "",
    val blog_name: String,
    val creation_date: LocalDate

)