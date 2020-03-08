package com.flashCards.mapper;

import com.flashCards.domain.FlashCard;
import com.flashCards.model.FlashCardDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FlashCardMapper {

    FlashCardMapper INSTANCE = Mappers.getMapper(FlashCardMapper.class);

    FlashCardDTO flashCardToFlashCardDTO(FlashCard flashCard);

    @Mapping(source = "categoryDTO", target = "category")
    FlashCard flashCardDTOToFlashCard(FlashCardDTO flashCardDTO);
}
