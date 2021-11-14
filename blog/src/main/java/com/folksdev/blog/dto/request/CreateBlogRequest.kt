package com.folksdev.blog.dto.request

import java.time.LocalDate
import javax.validation.constraints.*

data class CreateBlogRequest(

    @field:NotBlank
    val blog_name: String,

    @field:NotNull
    @field:PastOrPresent
    val creation_date: LocalDate,

)