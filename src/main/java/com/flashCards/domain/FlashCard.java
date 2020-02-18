package com.flashCards.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class FlashCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String level;
    private String front;
    private String back;
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "box_id")
    private Box box;
}
