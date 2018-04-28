package com.ddnet.graphql.normal.query;

import com.ddnet.graphql.normal.entity.Book;
import com.ddnet.graphql.normal.repository.BookRepository;
import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLName;
import graphql.schema.DataFetchingEnvironment;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Vinson.Ding on 2018/4/27.
 */
@GraphQLName("query")
public class BookQuery {
    private static BookRepository bookRepository = new BookRepository();

    @GraphQLField
    public static Book findOneBook(final DataFetchingEnvironment env, @NotNull @GraphQLName("id") long id) {
        return bookRepository.findById(id);
    }

    @GraphQLField
    public  static List<Book> searchBook(final DataFetchingEnvironment env, @NotNull @GraphQLName("name") String name) {
        return bookRepository.search(name);
    }
}
