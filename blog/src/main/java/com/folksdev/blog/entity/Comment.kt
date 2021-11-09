package com.folksdev.blog.entity

import org.hibernate.Hibernate
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Comment @JvmOverloads constructor(
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    val comment_id : String? = "",
    val comment_content: String,
    val comment_time: LocalDateTime,
    val post_id : String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id",referencedColumnName = "post_id",insertable = false,updatable = false)
    val post: Post,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commentator_id",referencedColumnName = "commentator_id",insertable = false,updatable = false)
    val commentator: Commentator? = null,


    val commentator_id: String,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Comment

        return comment_id != null && comment_id == other.comment_id
    }

    override fun hashCode(): Int = 0

    @Override
    override fun     toString(): String {
        return this::class.simpleName + "(comment_id = $comment_id )"
    }
}