package com.folksdev.blog.entity

import org.hibernate.annotations.GenericGenerator
import java.time.LocalDate
import javax.persistence.*

@Entity
data class Blog @JvmOverloads constructor(
    @Id
    @Column(name = "blog_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val blog_id: String? = "",
    val blog_name: String,
    val creation_date: LocalDate,


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "blog_authors",
        joinColumns = [JoinColumn(name = "blog_id", referencedColumnName = "blog_id")],
        inverseJoinColumns = [JoinColumn(name = "author_id", referencedColumnName = "author_id")]
    )
    val authors: Set<Author>? = HashSet(),


    @OneToMany(mappedBy = "author",fetch = FetchType.LAZY)
    val posts: Set<Post>? = HashSet(),


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "blog_commentators",
        joinColumns = [JoinColumn(name = "blog_id", referencedColumnName = "blog_id")],
        inverseJoinColumns = [JoinColumn(name = "commentator_id", referencedColumnName = "commentator_id")]
    )
    val commentators: Set<Commentator>? = HashSet(),

    )
