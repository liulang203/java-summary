package com.ddnet.graphql.boot.action;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.ddnet.graphql.boot.entity.Author;
import com.ddnet.graphql.boot.entity.Book;
import com.ddnet.graphql.boot.repository.AuthorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Created by Vinson.Ding on 2018/5/2.
 */
@Component
public class AuthorMutation implements GraphQLMutationResolver {
    @Autowired
    private AuthorDao authorDao;

    public Author writeAuthor(Long id, String name, LocalDate birthday) {
        Author author = new Author();
        author.setId(id);
        author.setName(name);
        author.setBirthday(birthday);

        return authorDao.saveAuthor(author);
    }

}
