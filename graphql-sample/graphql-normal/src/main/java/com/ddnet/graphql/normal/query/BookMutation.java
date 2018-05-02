package com.ddnet.graphql.normal.query;

import com.ddnet.graphql.normal.entity.Book;
import com.ddnet.graphql.normal.repository.BookRepository;
import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLName;
import graphql.schema.DataFetchingEnvironment;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * Created by Vinson.Ding on 2018/4/27.
 */
@GraphQLName("mutation")
public class BookMutation {
    private static BookRepository bookRepository = new BookRepository();

    @GraphQLField
    public static Book insertBook(final DataFetchingEnvironment env, @GraphQLName("book") Book book) {
        return bookRepository.createBook(Book.fromMap(env.getArgument("book")));
    }

    @GraphQLField
    public  static Book updateBook(final DataFetchingEnvironment env, @NotNull @GraphQLName("book") Book book) {
        return bookRepository.updateBook(Book.fromMap(env.getArgument("book")));
    }
}
