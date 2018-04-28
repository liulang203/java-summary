package com.ddnet.graphql.normal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.TypeResolutionEnvironment;
import graphql.schema.*;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import lombok.Getter;
import lombok.Setter;
import org.junit.Assert;
import org.junit.Test;

import static graphql.Scalars.GraphQLInt;
import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by Vinson.Ding on 2018/4/26.
 */
public class GraphqlSimpleTest {
    /**
     * type Book { name: String, price:int}
     */
    private  GraphQLObjectType bookType = newObject().name("bookType")
            .field(newFieldDefinition().name("name").type(GraphQLString))
            .field(newFieldDefinition().name("price").type(GraphQLInt))
            .build();

    /**
     *
     * @throws JsonProcessingException
     */
    @Test
    public void testHelloWord() throws JsonProcessingException {
        String schema = "type Query{hello: String}";
        SchemaParser schemaParser = new SchemaParser();
        TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(schema);

        RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring()
                .type("Query", builder -> builder.dataFetcher("hello", new StaticDataFetcher("word")))
                .build();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
        GraphQL graphQL = GraphQL.newGraphQL(graphQLSchema).build();
        ExecutionResult result = graphQL.execute("{hello}");
        Assert.assertEquals("{\"hello\":\"word\"}", new ObjectMapper().writeValueAsString(result.getData()));
    }

    @Test
    public void testType() {
        GraphQLFieldDefinition name = bookType.getFieldDefinition("name");
        Assert.assertNotNull(name);
        Assert.assertEquals(name.getType(), GraphQLString);
    }


    @Test
    public void testTypeResover() {
        TypeResolver resolver = env -> {
            Object data = env.getObject();
            if (data instanceof Book) {
                return env.getSchema().getObjectType("bookType");
            }
            return null;
        };
        GraphQLObjectType objectType = resolver.getType(new TypeResolutionEnvironment(new Book(),null,null,null,initSchema(),null));
        Assert.assertEquals(objectType,bookType);
        objectType = resolver.getType(new TypeResolutionEnvironment(new Integer(0),null,null,null,initSchema(),null));
        Assert.assertNull(objectType);
    }
    @Test
    public void testSchemaParse(){
        SchemaParser parser = new SchemaParser();
        SchemaGenerator generator = new SchemaGenerator();
        TypeDefinitionRegistry typeDefinitionRegistry = parser.parse("schema {\n" +
                "    query: QueryType\n" +
                "}\n" +
                "\n" +
                "type QueryType {\n" +
                "    hero(episode: Episode): Human\n" +
                "    human(id : String) : Human\n" +
                "}\n" +
                "\n" +
                "\n" +
                "enum Episode {\n" +
                "    NEWHOPE\n" +
                "    EMPIRE\n" +
                "    JEDI\n" +
                "}\n" +
                "\n" +
                "\n" +
                "type Human {\n" +
                "    id: ID!\n" +
                "    name: String!\n" +
                "    friends: [Human]\n" +
                "    appearsIn: [Episode]!\n" +
                "    homePlanet: String\n" +
                "}");
        RuntimeWiring runtimeWiring =RuntimeWiring.newRuntimeWiring().build();
        GraphQLSchema schema = generator.makeExecutableSchema(typeDefinitionRegistry,runtimeWiring);
        schema.getAdditionalTypes().stream().forEach(type->{
            System.out.println(type.getName());
        });

    }




    private GraphQLSchema initSchema() {
        return GraphQLSchema.newSchema().query(bookType).build();
    }

    @Getter
    @Setter
    public static class Book {
        private String name;
        private int price;
    }
}
