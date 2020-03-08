package com.flashCards.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BoxDTO {

    private Long id;
    private String name;
    private CategoryDTO categoryDTO;
    private List<FlashCardDTO> flashCardsDTO = new ArrayList<>();
}
