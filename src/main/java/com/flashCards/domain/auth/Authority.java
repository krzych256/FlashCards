package com.flashCards.domain.auth;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="authorities")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String authority;

}
