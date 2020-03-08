package com.flashCards.services;

import com.flashCards.model.FlashCardDTO;

import java.util.List;

public interface FlashCardService {

    List<FlashCardDTO> getAllFlashCards();

    FlashCardDTO getFlashCardById(Long id);

    FlashCardDTO createNewFlashCard(FlashCardDTO flashCardDTO);

    FlashCardDTO updateOrCreateFlashCard(Long id, FlashCardDTO flashCardDTO);

    void deleteById(Long id);
}
