package com.folksdev.blog.dto

import java.time.LocalDateTime

data class CommentDto(
    val comment_id: String? = "",
    val comment_content: String,
    val comment_time: LocalDateTime,
    val post_id: String,
    val commentator_id : String,
    /*val post: PostDto?,
    val commentator: CommentatorDto?,*/

)