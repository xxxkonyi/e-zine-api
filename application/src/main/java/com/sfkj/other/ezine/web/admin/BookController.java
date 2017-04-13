package com.sfkj.other.ezine.web.admin;

import com.querydsl.core.types.Predicate;
import com.sfkj.other.ezine.query.Book;
import com.sfkj.other.ezine.query.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/a/books")
public class BookController {

    private final BookRepository bookRepository;

    @RequestMapping
    public Iterable<Book> list(@QuerydslPredicate(root = Book.class) Predicate predicate, Pageable pageable) {
        return bookRepository.findAll(predicate, pageable);
    }

    @RequestMapping(value = "/{identifier}", method = RequestMethod.GET)
    public Book get(@PathVariable String identifier) {
        return bookRepository.findOne(identifier);
    }

    @RequestMapping(method = {RequestMethod.POST})
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Book dto) {
        Book book = new Book();
        book.setNumber(String.valueOf(bookRepository.count() + 1));
        book.setJournalNumber(dto.getJournalNumber());
        book.setName(dto.getName());
        book.setCoverUrl(dto.getCoverUrl());
        book.setPublisher(dto.getPublisher());
        book.setPublishedDate(dto.getPublishedDate());

        book.setCreatedTime(new Date());
        book.setUpdatedTime(new Date());
        bookRepository.save(book);
    }

    @RequestMapping(value = "/{identifier}", method = {RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String identifier) {
        bookRepository.delete(identifier);
    }

    @RequestMapping(value = "/{identifier}", method = {RequestMethod.PUT})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void change(@PathVariable String identifier,
                       @RequestBody Book dto) {
        Book book = new Book();
        book.setId(identifier);
        book.setJournalNumber(dto.getJournalNumber());
        book.setName(dto.getName());
        book.setCoverUrl(dto.getCoverUrl());
        book.setPublisher(dto.getPublisher());
        book.setPublishedDate(dto.getPublishedDate());

        book.setUpdatedTime(new Date());
        bookRepository.save(book);
    }

}
