package com.nejdetkadirr.springkotlinrestapi.domain

import org.jetbrains.annotations.NotNull
import javax.persistence.*

@Entity(name = "books")
data class Book (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @NotNull
    val title: String
)
