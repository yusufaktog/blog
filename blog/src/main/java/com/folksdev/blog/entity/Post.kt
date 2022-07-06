package com.folksdev.blog.entity

import org.hibernate.Hibernate
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDate
import javax.persistence.*

@Entity
data class Post @JvmOverloads constructor(
    @Id
    @Column(name = "post_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    val id : String? = "",
    val content : String,
    val time: LocalDate,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id") // her zaman many to one olan yere join column koy!
    val author: Author,

    @OneToMany(mappedBy = "post",fetch = FetchType.LAZY)
    val comments: Set<Comment>? = HashSet(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_id") // her zaman many to one olan yere join column koy!
    val blog: Blog

    ) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Post

        return id != null && id == other.id
    }

    override fun hashCode(): Int = 0

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(post_id = $id )"
    }
}