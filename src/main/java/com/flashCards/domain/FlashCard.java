package com.flashCards.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class FlashCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String level;

    @Column
    private String front;

    @Column
    private String back;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn
    private Category category;

    @ManyToMany
    @JoinTable(name = "flash_card_box",
            joinColumns = @JoinColumn(name = "flash_card_id"),
            inverseJoinColumns = @JoinColumn(name = "box_id")
    )
    private List<Box> boxes = new ArrayList<>();
}
