package com.ddnet.graphql.boot.repository;

import com.ddnet.graphql.boot.entity.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Created by Vinson.Ding on 2018/5/2.
 */
@Component
public class BookDao {
    private AtomicLong idGenerator = new AtomicLong(1L);
    private List<Book> books = new ArrayList<>();


    public List<Book> getRecentBooks(int count, int offset) {
        return books.stream().skip(offset).limit(count).collect(Collectors.toList());
    }

    public List<Book> getBookAuthors(Long authorId) {
        return books.stream().filter(post -> authorId.equals(post.getAuthorId())).collect(Collectors.toList());
    }

    public Book saveBook(Book book) {
        if (book.getId() == null) {
            book.setId(idGenerator.getAndIncrement());
            books.add(0, book);
            return book;
        } else {
            Optional<Book> dbBookOptional = books.stream().filter(book1 -> book.getId().equals(book1.getId())).findFirst();
            if (dbBookOptional.isPresent()) {
                Book dbBook = dbBookOptional.get();
                dbBook.setAuthorId(book.getAuthorId());
                dbBook.setName(book.getName());
                dbBook.setPrice(book.getPrice());
                return dbBook;
            }
        }
        return null;
    }

}
