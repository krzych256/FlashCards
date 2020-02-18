package com.flashCards.model;

import com.flashCards.domain.Box;
import com.flashCards.domain.Category;
import lombok.Data;

@Data
public class FlashCardDTO {

    private Long id;
    private String level;
    private String front;
    private String back;
    private String description;
    private Category category;
    private Box box;
}
