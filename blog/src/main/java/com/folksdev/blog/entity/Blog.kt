package com.folksdev.blog.entity

import org.hibernate.Hibernate
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDate
import javax.persistence.*

@Entity
data class Blog @JvmOverloads constructor(
    @Id
    @Column(name = "blog_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",
    val name: String,
    val creationDate: LocalDate,

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

    ) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Blog

        return id != null && id == other.id
    }

    override fun hashCode(): Int = 0

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(blog_id = $id )"
    }
}
