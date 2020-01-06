package com.ha.reactiveredis.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
public class Person implements Serializable {

    @Id
    private Long id;

    private String title;

    private String content;
}
