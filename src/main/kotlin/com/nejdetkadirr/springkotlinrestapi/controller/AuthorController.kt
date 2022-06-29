package com.nejdetkadirr.springkotlinrestapi.controller

import com.nejdetkadirr.springkotlinrestapi.domain.Author
import com.nejdetkadirr.springkotlinrestapi.repository.AuthorRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/authors")
class AuthorController(private val authorRepository: AuthorRepository) {
    @PostMapping
    fun create(@Validated @RequestBody author: Author) : ResponseEntity<Author> {
        return ResponseEntity.ok(authorRepository.save(author))
    }

    @GetMapping
    fun index() : ResponseEntity<List<Author>> {
        return ResponseEntity.ok(authorRepository.findAll().toList())
    }

    @GetMapping("{id}")
    fun show(@PathVariable(value = "id") authorId: Long) : ResponseEntity<Author> {
        return authorRepository
                .findById(authorId)
                .map { author ->
                    ResponseEntity.ok(author)
                }
                .orElse(ResponseEntity.notFound().build())
    }

    @PatchMapping("{id}")
    fun update(@PathVariable(value = "id") authorId: Long, @Validated @RequestBody newAuthor: Author) : ResponseEntity<Author> {
        return authorRepository
                .findById(authorId)
                .map { author ->
                    ResponseEntity.ok(authorRepository.save(author.copy(fullName = newAuthor.fullName)))
                }
                .orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("{id}")
    fun delete(@PathVariable(value = "id") authorId: Long) : ResponseEntity<Void> {
        return authorRepository
                .findById(authorId)
                .map { author ->
                    authorRepository.delete(author)
                    ResponseEntity<Void>(HttpStatus.NO_CONTENT)
                }
                .orElse(ResponseEntity.notFound().build())
    }
}
