package com.flashCards.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Box {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToMany
    @JoinTable(name = "profile_box",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "box_id")
    )
    private List<Profile> profiles = new ArrayList<>();

    @ManyToOne
    @JoinColumn
    private Category category;

    @ManyToMany(mappedBy = "boxes")
    private List<FlashCard> flashCards = new ArrayList<>();
}
