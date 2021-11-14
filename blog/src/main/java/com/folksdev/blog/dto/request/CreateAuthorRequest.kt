package com.folksdev.blog.dto.request

import com.folksdev.blog.entity.Author
import java.time.LocalDate
import java.time.LocalDateTime
import javax.validation.constraints.*

data class CreateAuthorRequest(

    @field:NotBlank
    val author_name: String,

    @field:Email
    val email: String,

    @field: PastOrPresent
    val dateOfBirth: LocalDate,

    val gender: Author.Gender,

    @field: PastOrPresent
    val auth_date: LocalDateTime


)
