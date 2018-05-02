package com.ddnet.graphql.normal;

import com.ddnet.graphql.normal.datetime.DateScalars;
import com.ddnet.graphql.normal.query.BookQuery;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.language.ScalarTypeDefinition;
import graphql.parser.antlr.GraphqlParser;
import graphql.schema.GraphQLScalarType;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

/**
 * Created by Vinson.Ding on 2018/4/27.
 */
@Slf4j
public class SchemaGraphQL {
    public GraphQLSchema bookSchema() throws IOException {
        DateScalars.initScalars();

        Reader file = new InputStreamReader(SchemaGraphQL.class.getClassLoader().getResource("book.graphql").openStream(), "UTF-8");

        SchemaParser schemaParser = new SchemaParser();
        TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(file);

        RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring()
                .type("BookQuery", buider -> buider.dataFetcher("findOneBook", environment -> BookQuery.findOneBook(environment, environment.getArgument("id"))))
                .type("BookQuery", buider -> buider.dataFetcher("searchBook", environment -> BookQuery.searchBook(environment, environment.getArgument("name"))))
                .build();

        SchemaGenerator schemaGenerator = new SchemaGenerator();
        GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
        return graphQLSchema;
    }

    public ExecutionResult search(String query, Map<String, Object> field) {
        GraphQL graphQL ;
        try {
            graphQL = GraphQL.newGraphQL(bookSchema()).build();
        } catch (IOException e) {
            log.error("", e);
            return null;
        }
        ExecutionInput input = ExecutionInput.newExecutionInput().query(query).variables(field).build();
        return graphQL.execute(input);
    }
}
