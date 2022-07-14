package com.folksdev.blog.dto.request

import com.folksdev.blog.entity.Author
import java.time.LocalDate
import java.time.LocalDateTime
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.PastOrPresent

data class CreateAuthorRequest(

    @field:NotBlank
    val name: String,

    @field:Email
    val email: String,

    @field: PastOrPresent
    val dateOfBirth: LocalDate,

    val gender: Author.Gender,

    @field: PastOrPresent
    val authDate: LocalDateTime


)
