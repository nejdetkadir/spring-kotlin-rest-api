package com.nejdetkadirr.springkotlinrestapi.repository

import com.nejdetkadirr.springkotlinrestapi.domain.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : JpaRepository<Book, Long> {
}
