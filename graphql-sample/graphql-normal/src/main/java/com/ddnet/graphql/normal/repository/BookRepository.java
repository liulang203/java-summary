package com.ddnet.graphql.normal.repository;

import com.ddnet.graphql.normal.entity.Author;
import com.ddnet.graphql.normal.entity.Book;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Created by Vinson.Ding on 2018/4/27.
 */

public class BookRepository {
    private List<Book> books = new ArrayList<>();
    private List<Author> authors = new ArrayList<>();
    AtomicLong idGenerator = new AtomicLong(1L);


    public BookRepository() {
        initData();
    }

    public Book findById(long id) {
        for (Book b : books) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null;
    }

    public List<Book> search(String name) {
        return books.stream().filter(book -> book.getName().contains(name)).collect(Collectors.toList());
    }

    public void initData() {
        Book haibe = new Book(idGenerator.getAndIncrement(), "abc", 400, new ArrayList<>());
        books.add(haibe);
        Book hali = new Book(idGenerator.getAndIncrement(), "bcd", 450, new ArrayList<>());
        books.add(hali);
        Book jaocai = new Book(idGenerator.getAndIncrement(), "def", 470, new ArrayList<>());
        books.add(jaocai);

        Author a1 = new Author(idGenerator.getAndIncrement(), "A1", LocalDate.of(1964, 11, 22), new ArrayList<>(), new ArrayList<>());
        authors.add(a1);
        Author a2 = new Author(idGenerator.getAndIncrement(), "A2", LocalDate.of(1974, 11, 22), new ArrayList<>(), new ArrayList<>());
        authors.add(a2);
        Author a3 = new Author(idGenerator.getAndIncrement(), "A3", LocalDate.of(1984, 11, 22), new ArrayList<>(), new ArrayList<>());
        authors.add(a3);
        a1.addFriend(a2);
        a1.addFriend(a3);
        a2.addFriend(a1);
        a3.addFriend(a1);
        haibe.addAuthor(a1);
        hali.addAuthor(a2);
        jaocai.addAuthor(a1);
        jaocai.addAuthor(a3);

    }

    public Book createBook(@NotNull Book book) {
        book.setId(idGenerator.getAndIncrement());
        books.add(book);
        return null;
    }

    public Book updateBook(@NotNull Book book) {
        Book searchBook = findById(book.getId());
        if (searchBook != null) {
            searchBook.setName(book.getName());
            searchBook.setPrice(book.getPrice());
            return searchBook;
        }
        return null;
    }
}
