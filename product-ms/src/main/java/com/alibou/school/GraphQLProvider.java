package com.alibou.school;

import graphql.GraphQL;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeRuntimeWiring;
import org.hibernate.boot.model.TypeDefinitionRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class GraphQLProvider {
    @Bean
    public GraphQL graphQL(ProductGraphQLResolver resolver) {
        RuntimeWiring wiring = RuntimeWiring.newRuntimeWiring()
                .type(TypeRuntimeWiring.newTypeWiring("Query")
                        .dataFetcher("products", resolver::getProducts))
                .build();

        SchemaParser schemaParser = new SchemaParser();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        TypeDefinitionRegistry typeRegistry = schemaParser.parse(new File("schema.graphqls"));

        return GraphQL.newGraphQL(schemaGenerator.makeExecutableSchema(typeRegistry, wiring)).build();
    }
}
