package com.ddnet.graphql.normal;

import com.ddnet.graphql.normal.entity.Author;
import com.ddnet.graphql.normal.entity.Book;
import com.ddnet.graphql.normal.query.BookMutation;
import com.ddnet.graphql.normal.query.BookQuery;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.annotations.processor.GraphQLAnnotations;
import graphql.schema.GraphQLSchema;
import graphql.schema.GraphQLType;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Vinson.Ding on 2018/4/27.
 */
public class AnnotationGraphQL {
    public GraphQLSchema bookSchema() {
//        Set<GraphQLType> types = new HashSet<>();
//        types.add(GraphQLAnnotations.object(Book.class));
//        types.add(GraphQLAnnotations.object(Author.class));

        return GraphQLSchema.newSchema()
//                .additionalTypes(types)
                .query(GraphQLAnnotations.object(BookQuery.class))
                .mutation(GraphQLAnnotations.object(BookMutation.class))
                .build();
    }

    public ExecutionResult search(String query, Map<String, Object> field) {
        GraphQL graphQL = GraphQL.newGraphQL(bookSchema()).build();
        ExecutionInput input = ExecutionInput.newExecutionInput().query(query).variables(field).build();
        return graphQL.execute(input);
    }
}
