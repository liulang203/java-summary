package com.ddnet.graphql.boot.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.ddnet.graphql.boot.entity.Author;
import com.ddnet.graphql.boot.entity.Book;
import com.ddnet.graphql.boot.repository.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Vinson.Ding on 2018/5/2.
 */
@Component
public class AuthorResolver implements GraphQLResolver<Author> {
    @Autowired
    private BookDao bookDao;

    public List<Book> getBooks(Author author) {
        return bookDao.getBookAuthors(author.getId());
    }
}
