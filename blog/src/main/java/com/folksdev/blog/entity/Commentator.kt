package com.folksdev.blog.entity

import org.hibernate.Hibernate
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


    @OneToMany(mappedBy = "commentator", fetch = FetchType.LAZY)
    val comments: Set<Comment>? = HashSet(),

    @ManyToMany
    @JoinColumn(name = "blog_id", referencedColumnName = "blog_id")
    var blogs: Set<Blog>

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Commentator

        return commentator_id != null && commentator_id == other.commentator_id
    }

    override fun hashCode(): Int = 0

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(commentator_id = $commentator_id )"
    }
}