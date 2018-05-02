package com.ddnet.graphql.boot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Created by Vinson.Ding on 2018/4/27.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    private Long id;
    private String name;
    private LocalDate birthday;

}
