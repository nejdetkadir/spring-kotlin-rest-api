package com.nejdetkadirr.springkotlinrestapi.controller

import com.nejdetkadirr.springkotlinrestapi.domain.Book
import com.nejdetkadirr.springkotlinrestapi.repository.BookRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/books")
class BookController(val bookRepository: BookRepository) {
    @PostMapping
    fun create(@Validated @RequestBody book: Book) : ResponseEntity<Book> {
        return ResponseEntity.ok(bookRepository.save(book))
    }

    @GetMapping
    fun index() : ResponseEntity<List<Book>> {
        return ResponseEntity.ok(bookRepository.findAll().toList())
    }

    @GetMapping("{id}")
    fun show(@PathVariable(value = "id") bookId : Long) : ResponseEntity<Book> {
        return bookRepository
                .findById(bookId)
                .map { book -> ResponseEntity.ok(book) }
                .orElse(ResponseEntity.notFound().build())
    }

    @PatchMapping("{id}")
    fun update(@PathVariable(value = "id") bookId: Long, @Validated @RequestBody newBook: Book) : ResponseEntity<Book> {
        return bookRepository
                .findById(bookId)
                .map { existBook ->
                    ResponseEntity.ok(bookRepository.save(existBook.copy(title = newBook.title)))
                }
                .orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("{id}")
    fun delete(@PathVariable(value = "id") bookId: Long) : ResponseEntity<Void> {
        return bookRepository
                .findById(bookId)
                .map { book ->
                    bookRepository.delete(book)
                    ResponseEntity<Void>(HttpStatus.NO_CONTENT)
                }
                .orElse(ResponseEntity.notFound().build())
    }
}