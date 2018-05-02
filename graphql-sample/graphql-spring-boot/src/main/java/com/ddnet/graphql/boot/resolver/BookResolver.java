package com.ddnet.graphql.boot.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.ddnet.graphql.boot.entity.Author;
import com.ddnet.graphql.boot.entity.Book;
import com.ddnet.graphql.boot.repository.AuthorDao;
import com.ddnet.graphql.boot.repository.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by Vinson.Ding on 2018/5/2.
 */
@Component
public class BookResolver implements GraphQLResolver<Book> {
    @Autowired
    private AuthorDao authorDao;

    public Optional<Author> getAuthor(Book book) {
        return authorDao.getAuthor(book.getAuthorId());
    }
}
