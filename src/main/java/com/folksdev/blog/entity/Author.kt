package com.folksdev.blog.entity

import org.hibernate.Hibernate
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*


@Entity
data class Author @JvmOverloads constructor(

    @Id
    @Column(name = "author_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",
    val name: String,
    val email: String,
    val dateOfBirth: LocalDate,
    val gender: Gender,
    val authDate: LocalDateTime,

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    val posts: Set<Post>? = HashSet(),

    @ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY)
    val blogs: Set<Blog>

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Author

        return id != null && id == other.id
    }

    override fun hashCode(): Int = 0

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(author_id = $id )"
    }
    enum class Gender {
        MALE, FEMALE, UNKOWN
    }
}

