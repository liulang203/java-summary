package com.ddnet.graphql.normal.entity;

import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Vinson.Ding on 2018/4/27.
 */
@GraphQLName("Author")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    @GraphQLField
    private Long id;
    @GraphQLField
    private String name;
    @GraphQLField
    private LocalDate birthday;

    @GraphQLField
    private List<Author> friends;
    @GraphQLField
    private List<Book> books;

    public void addBooks(Book book) {
        books.add(book);
    }

    public void addFriend(Author friend) {
        friends.add(friend);
    }
}
