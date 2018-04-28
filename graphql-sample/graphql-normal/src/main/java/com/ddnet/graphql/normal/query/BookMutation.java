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
@GraphQLName("mutation")
public class BookMutation {
    private static BookRepository bookRepository = new BookRepository();

    @GraphQLField
    public static Book insertBook(final DataFetchingEnvironment env, @NotNull @GraphQLName("book") Book book) {
        return bookRepository.createBook(book);
    }

    @GraphQLField
    public  static Book updateBook(final DataFetchingEnvironment env, @NotNull @GraphQLName("book") Book book) {
        return bookRepository.updateBook(book);
    }
}
