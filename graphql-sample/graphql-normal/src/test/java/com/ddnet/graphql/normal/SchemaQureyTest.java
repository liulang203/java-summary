package com.ddnet.graphql.normal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.ExecutionResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vinson.Ding on 2018/4/28.
 */
@Slf4j
public class SchemaQureyTest  {
    @Test
    public void findOneBook() throws Exception {
        SchemaGraphQL bookSchema = new SchemaGraphQL();
        Map<String,Object> params = new HashMap<>();
        params.put("id",1L);
        String query = "query($id: Long!){ findOneBook ( id: $id) { id name} }";
        query(bookSchema, params, query);
        query = "query($id: Long!){ findOneBook ( id: $id) { id name authors{id name birthday}} }";
        query(bookSchema, params, query);
    }
    @Test
    public void searchBook() throws Exception {
        SchemaGraphQL bookSchema = new SchemaGraphQL();
        Map<String,Object> params = new HashMap<>();
        params.put("name","b");
        String query = "query($name: String!){ searchBook ( name: $name) { id name} }";
        query(bookSchema, params, query);
        query = "query($name: String!){ searchBook ( name: $name) { id name authors{id name friends{id name birthday}}} }";
        query(bookSchema, params, query);
    }

    private void query(SchemaGraphQL bookSchema, Map<String, Object> params, String query) throws JsonProcessingException {
        ExecutionResult res = bookSchema.search(query,params);
        if(res.getErrors()!=null){
            res.getErrors().forEach(ex->log.error("",ex));
        }
        System.out.println(new ObjectMapper().writeValueAsString(res));
    }

}