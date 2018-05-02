package com.ddnet.graphql.boot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Vinson.Ding on 2018/4/27.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private Long id;
    private String name;
    private long price;
    private Long authorId;

}
