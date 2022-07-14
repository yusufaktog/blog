package com.folksdev.blog.dto.summarizeddtos

import com.folksdev.blog.entity.Author
import java.time.LocalDate

data class SummarizedAuthorDto(
    val id: String? = "",
    val name: String,
    val email: String,
    val dateOfBirth: LocalDate?,
    val gender: Author.Gender,
)
