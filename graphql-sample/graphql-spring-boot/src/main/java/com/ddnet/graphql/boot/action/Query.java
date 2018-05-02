package com.ddnet.graphql.boot.action;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.ddnet.graphql.boot.entity.Book;
import com.ddnet.graphql.boot.repository.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Vinson.Ding on 2018/5/2.
 */
@Component
public class Query implements GraphQLQueryResolver {
    @Autowired
    private BookDao bookDao;

    public List<Book> recentBooks(int count, int offset) {
        return bookDao.getRecentBooks(count, offset);
    }
}
