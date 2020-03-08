package com.flashCards.model;

import lombok.Data;

@Data
public class FlashCardDTO {

    private Long id;
    private String level;
    private String front;
    private String back;
    private String description;
    private CategoryDTO categoryDTO;
}
