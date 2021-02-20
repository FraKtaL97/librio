package com.jovcevski.librio.web.controller;

import com.jovcevski.librio.web.entity.Book;
import com.jovcevski.librio.web.exception.BookNotFoundException;
import com.jovcevski.librio.web.repository.BookRepository;
import com.jovcevski.librio.web.request.CreateBookRequest;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("api/books")
@Slf4j
public class BookController {
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }
    @GetMapping
    public List<Book> getBooks(){
        return bookRepository.findAll();
    }
    @GetMapping("{id}")
    public Book getBook(@PathVariable long id) throws BookNotFoundException {
        Optional<Book> book = bookRepository.findById(id);
        if(book.isEmpty()){
            throw new BookNotFoundException();
        }
        return book.get();
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody CreateBookRequest createBookRequest){
        Book book = new Book(createBookRequest.getTitle(),
                             createBookRequest.getDescription(),
                             createBookRequest.getAuthor());

        return new ResponseEntity<>(bookRepository.save(book), HttpStatus.CREATED);
    }
}
