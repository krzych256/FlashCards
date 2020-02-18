package com.flashCards.model;

import com.flashCards.domain.Category;
import com.flashCards.domain.FlashCard;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BoxDTO {

    private Long id;
    private String name;
    private Category category;
    private List<FlashCard> flashCards = new ArrayList<>();
}
