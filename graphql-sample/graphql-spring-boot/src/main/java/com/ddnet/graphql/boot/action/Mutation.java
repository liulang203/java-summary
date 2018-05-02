package com.ddnet.graphql.boot.action;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.ddnet.graphql.boot.entity.Book;
import com.ddnet.graphql.boot.repository.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Vinson.Ding on 2018/5/2.
 */
@Component
public class Mutation implements GraphQLMutationResolver {
    @Autowired
    private BookDao bookDao;

    public Book writeBook(Long id, String name, long price, Long authorId) {
        Book book = new Book();
        book.setId(id);
        book.setName(name);
        book.setPrice(price);
        book.setAuthorId(authorId);

        return bookDao.saveBook(book);
    }
    public Book newBook(Book book){
        return bookDao.saveBook(book);
    }
}
