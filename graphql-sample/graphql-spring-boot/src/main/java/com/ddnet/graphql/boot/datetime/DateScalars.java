package com.ddnet.graphql.boot.datetime;

import graphql.language.ScalarTypeDefinition;
import graphql.schema.GraphQLScalarType;
import graphql.schema.idl.ScalarInfo;

/**
 * Created by Vinson.Ding on 2018/4/27.
 */
public  class DateScalars {
    public static final GraphQLScalarType GraphQLDate = new GraphQLDate();
    public static final GraphQLScalarType GraphQLLocalDate = new GraphQLLocalDate();
    public static final GraphQLScalarType GraphQLLocalDateTime = new GraphQLLocalDateTime();
    public static final GraphQLScalarType GraphQLLocalTime = new GraphQLLocalTime();

    public static void initScalars(){
        ScalarInfo.STANDARD_SCALARS.add(DateScalars.GraphQLDate);
        ScalarInfo.STANDARD_SCALARS.add(DateScalars.GraphQLLocalDate);
        ScalarInfo.STANDARD_SCALARS.add(DateScalars.GraphQLLocalDateTime);
        ScalarInfo.STANDARD_SCALARS.add(DateScalars.GraphQLLocalTime);
        ScalarInfo.STANDARD_SCALAR_DEFINITIONS.put("Date",new ScalarTypeDefinition("Date"));
        ScalarInfo.STANDARD_SCALAR_DEFINITIONS.put("LocalDate",new ScalarTypeDefinition("LocalDate"));
        ScalarInfo.STANDARD_SCALAR_DEFINITIONS.put("LocalDateTime",new ScalarTypeDefinition("LocalDateTime"));
        ScalarInfo.STANDARD_SCALAR_DEFINITIONS.put("LocalTime",new ScalarTypeDefinition("LocalTime"));
    }
}
