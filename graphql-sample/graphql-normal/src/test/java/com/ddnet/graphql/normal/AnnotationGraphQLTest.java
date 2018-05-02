package com.ddnet.graphql.normal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.ExecutionResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vinson.Ding on 2018/4/27.
 */
@Slf4j
public class AnnotationGraphQLTest {
    @Test
    public void findOneBook() throws Exception {
        AnnotationGraphQL bookSchema = new AnnotationGraphQL();
        Map<String, Object> params = new HashMap<>();
        params.put("id", 1L);
        String query = "query($id: Long!){ findOneBook ( id: $id) { id name} }";
        query(bookSchema, params, query);
        query = "query($id: Long!){ findOneBook ( id: $id) { id name authors{id name birthday}} }";
        query(bookSchema, params, query);
    }

    @Test
    public void searchBook() throws Exception {
        AnnotationGraphQL bookSchema = new AnnotationGraphQL();
        Map<String, Object> params = new HashMap<>();
        params.put("name", "b");
        String query = "query($name: String!){ searchBook ( name: $name) { id name} }";
        query(bookSchema, params, query);
        query = "query($name: String!){ searchBook ( name: $name) { id name authors{id name birthday}} }";
        query(bookSchema, params, query);
    }

    @Test
    public void createBook() throws Exception {
        AnnotationGraphQL bookSchema = new AnnotationGraphQL();
        Map<String, Object> book = new HashMap<>();
        book.put("name", "The World According to Garp");
        book.put("price", 1234L);
        Map<String, Object> params = new HashMap<>();
        params.put("book", book);
        String query = "mutation mutation($book: InputBook!){ insertBook ( book: $book) { id name price} }";
        query(bookSchema, params, query);
    }

    @Test
    public void updateBook() throws Exception {
        AnnotationGraphQL bookSchema = new AnnotationGraphQL();
        Map<String, Object> book = new HashMap<>();
        book.put("id", 1L);
        book.put("name", "Harry Potter and the Philosopher's Stone");
        book.put("price", 12134L);
        Map<String, Object> params = new HashMap<>();
        params.put("book", book);
        String query = "mutation mutation($book: InputBook!){ updateBook ( book: $book) { id name price} }";
        query(bookSchema, params, query);
    }


    private void query(AnnotationGraphQL bookSchema, Map<String, Object> params, String query) throws JsonProcessingException {
        ExecutionResult res = bookSchema.search(query, params);
        if (res.getErrors() != null) {
            res.getErrors().forEach(ex -> log.error("", ex));
        }
        System.out.println(new ObjectMapper().writeValueAsString(res));
    }


}