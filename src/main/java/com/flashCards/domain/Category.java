package com.flashCards.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Set<Box> boxes;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Set<FlashCard> flashCards;
}
