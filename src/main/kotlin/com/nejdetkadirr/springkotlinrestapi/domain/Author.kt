package com.nejdetkadirr.springkotlinrestapi.domain

import org.jetbrains.annotations.NotNull
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "authors")
data class Author (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @NotNull
    val fullName: String,
)
