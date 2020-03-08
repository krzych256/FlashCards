package com.flashCards.services;

import com.flashCards.model.BoxDTO;

import java.util.List;

public interface BoxService {

    List<BoxDTO> getAllBoxes();

    BoxDTO getBoxById(Long id);

    BoxDTO createNewBox(BoxDTO boxDTO);

    BoxDTO updateOrCreateBox(Long id, BoxDTO boxDTO);

    void deleteById(Long id);
}
