package com.ddnet.graphql.normal.entity;

import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.NonFinal;

import java.util.List;

/**
 * Created by Vinson.Ding on 2018/4/27.
 */
@GraphQLName("Book")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @GraphQLField
    private Long id;
    @GraphQLField
    private String name;
    @GraphQLField
    private long price;
    @GraphQLField
    private List<Author> authors;

    public void addAuthor(Author author) {
        authors.add(author);
        author.addBooks(this);
    }
}
