package com.folksdev.blog.entity

import org.hibernate.annotations.GenericGenerator
import java.time.LocalDate
import javax.persistence.*

@Entity
data class Post @JvmOverloads constructor(
    @Id
    @Column(name = "post_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    val post_id : String?,
    val post_content : String,
    val post_date: LocalDate,
    val author_id: String,
    val blog_id: String,



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id",referencedColumnName = "author_id") // her zaman many to one olan yere join column koy!
    val author: Author,

    @OneToMany(mappedBy = "post",fetch = FetchType.LAZY)
    val comments: Set<Comment>? = HashSet(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_id",referencedColumnName = "blog_id") // her zaman many to one olan yere join column koy!
    val blog: Blog

    ) {
}