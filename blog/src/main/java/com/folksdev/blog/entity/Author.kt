package com.folksdev.blog.entity

import org.hibernate.annotations.GenericGenerator
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*
import kotlin.collections.HashSet


@Entity
data class Author @JvmOverloads constructor(

    @Id
    @Column(name = "author_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    val author_id : String?,
    val name: String,
    val dateOfBirth: LocalDate,
    val gender: Gender,
    val auth_date: LocalDateTime,
    val blog_id: String,

    @OneToMany(mappedBy = "author",fetch = FetchType.LAZY)
    val posts: Set<Post>? = HashSet(),

    @ManyToMany(mappedBy = "authors",fetch = FetchType.LAZY)
    val blogs: Set<Blog>

)

enum class Gender{
    MALE,FEMALE,UNKOWN
}