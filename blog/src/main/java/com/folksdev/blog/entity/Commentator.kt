package com.folksdev.blog.entity

import org.hibernate.annotations.GenericGenerator
import java.time.LocalDate
import javax.persistence.*

@Entity
data class Commentator @JvmOverloads constructor(
    @Id
    @Column(name = "commentator_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val commentator_id: String? = "",
    val commentator_name: String,
    val auth_date: LocalDate,
    val blog_id: String,


    @OneToMany(mappedBy = "commentator", fetch = FetchType.LAZY)
    val comments: Set<Comment>? = HashSet(),

    @ManyToMany
    @JoinColumn(name = "blog_id", referencedColumnName = "blog_id")
    var blogs: Set<Blog>

)