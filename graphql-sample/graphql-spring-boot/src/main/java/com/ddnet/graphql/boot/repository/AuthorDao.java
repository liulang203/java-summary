package com.ddnet.graphql.boot.repository;

import com.ddnet.graphql.boot.entity.Author;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Vinson.Ding on 2018/5/2.
 */
@Component
public class AuthorDao {
    private AtomicLong idGenerator = new AtomicLong(1L);
    private List<Author> authors = new ArrayList<>();

    public Optional<Author> getAuthor(Long id) {
        return authors.stream().filter(author -> id.equals(author.getId())).findFirst();
    }

    public Author saveAuthor(Author author) {
        if (author.getId() == null) {
            author.setId(idGenerator.getAndIncrement());
            authors.add(author);
            return author;
        }
        return updateAuthor(author);
    }

    private Author updateAuthor(Author author) {
        Optional<Author> authorOptional = authors.stream()
                .filter(author1 -> author.getId().equals(author1.getId()))
                .findFirst();
        if (authorOptional.isPresent()) {
            Author dbAuthor = authorOptional.get();
            dbAuthor.setName(author.getName());
            dbAuthor.setBirthday(author.getBirthday());
            return dbAuthor;
        }
        return null;
    }
}
