package com.norbi930523.trello.user.model

import com.norbi930523.trello.common.model.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "USER")
open class User : BaseEntity() {

    @Column(name = "USERNAME", nullable = false, unique = true)
    open lateinit var username: String

    @Column(name = "PASSWORD", nullable = false)
    open lateinit var password: String

}

