package com.nejdetkadirr.springkotlinrestapi.repository

import com.nejdetkadirr.springkotlinrestapi.domain.Author
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorRepository : JpaRepository<Author, Long> {
}
