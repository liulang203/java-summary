package com.ddnet.graphql.normal;

import com.ddnet.graphql.normal.datetime.DateScalars;
import com.ddnet.graphql.normal.entity.Book;
import com.ddnet.graphql.normal.query.BookMutation;
import com.ddnet.graphql.normal.query.BookQuery;
import com.ddnet.graphql.normal.repository.BookRepository;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.Scalars;
import graphql.schema.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Vinson.Ding on 2018/4/27.
 */
public class NormalGraphQL {
    public GraphQLSchema bookSchema() {
        GraphQLObjectType bookType = GraphQLObjectType.newObject().name("Book")
                .field(GraphQLFieldDefinition.newFieldDefinition().name("id").type(Scalars.GraphQLLong).build())
                .field(GraphQLFieldDefinition.newFieldDefinition().name("name").type(Scalars.GraphQLString).build())
                .field(GraphQLFieldDefinition.newFieldDefinition().name("price").type(Scalars.GraphQLLong).build())
                .field(GraphQLFieldDefinition.newFieldDefinition().name("authors").type(new GraphQLList(new GraphQLTypeReference("Author"))).build())
                .build();

        GraphQLObjectType authorType = GraphQLObjectType.newObject().name("Author")
                .field(GraphQLFieldDefinition.newFieldDefinition().name("id").type(Scalars.GraphQLLong).build())
                .field(GraphQLFieldDefinition.newFieldDefinition().name("name").type(Scalars.GraphQLString).build())
                .field(GraphQLFieldDefinition.newFieldDefinition().name("birthday").type(DateScalars.GraphQLLocalDate).build())
                .field(GraphQLFieldDefinition.newFieldDefinition().name("friends").type(new GraphQLList(new GraphQLTypeReference("Author"))).build())
                .field(GraphQLFieldDefinition.newFieldDefinition().name("books").type(new GraphQLList(new GraphQLTypeReference("Book"))).build()).build();

        GraphQLInputObjectType bookInput = GraphQLInputObjectType.newInputObject().name("InputBook")
                .field(GraphQLInputObjectField.newInputObjectField().name("id").type(Scalars.GraphQLLong).build())
                .field(GraphQLInputObjectField.newInputObjectField().name("name").type(Scalars.GraphQLString).build())
                .field(GraphQLInputObjectField.newInputObjectField().name("price").type(Scalars.GraphQLLong).build())
                .build();

        Set<GraphQLType> types = new HashSet<>();
        types.add(bookType);
        types.add(authorType);
        types.add(bookInput);


        GraphQLSchema schema = GraphQLSchema.newSchema().additionalTypes(types)
                .query(GraphQLObjectType.newObject().name("query")
                        .field(GraphQLFieldDefinition.newFieldDefinition().name("findOneBook")
                                .argument(GraphQLArgument.newArgument().name("id").type(Scalars.GraphQLLong).defaultValue(1L))
                                .type(new GraphQLTypeReference("Book"))
                                .dataFetcher(environment -> BookQuery.findOneBook(environment, environment.getArgument("id"))))
                        .field(GraphQLFieldDefinition.newFieldDefinition().name("searchBook")
                                .type(new GraphQLList(new GraphQLTypeReference("Book")))
                                .argument(GraphQLArgument.newArgument().name("name").type(Scalars.GraphQLString).defaultValue(""))
                                .dataFetcher(environment -> BookQuery.searchBook(environment, environment.getArgument("name"))))
                )
                .mutation(GraphQLObjectType.newObject().name("BookMutation")
                        .field(GraphQLFieldDefinition.newFieldDefinition().name("insertBook")
                                .type(new GraphQLTypeReference("Book"))
                                .argument(GraphQLArgument.newArgument().name("book").type(new GraphQLTypeReference("InputBook")))
                                .dataFetcher(environment -> new BookRepository().createBook(Book.fromMap(environment.getArgument("book")))))
                        .field(GraphQLFieldDefinition.newFieldDefinition().name("updateBook")
                                .type(new GraphQLTypeReference("Book"))
                                .argument(GraphQLArgument.newArgument().name("book").type(new GraphQLTypeReference("InputBook")))
                                .dataFetcher(environment -> new BookRepository().updateBook( Book.fromMap(environment.getArgument("book")))))
                ).build();

        return schema;
    }

    public ExecutionResult search(String query, Map<String, Object> field) {
        GraphQL graphQL = GraphQL.newGraphQL(bookSchema()).build();
        ExecutionInput input = ExecutionInput.newExecutionInput().query(query).variables(field).build();
        return graphQL.execute(input);
    }
}
