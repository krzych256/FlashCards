package com.flashCards.services;

import com.flashCards.model.BoxDTO;

import java.util.List;

public interface BoxService {

    BoxDTO findById(long id);

    List<BoxDTO> findAll();

    BoxDTO save(BoxDTO boxDTO);

    void delete(BoxDTO boxDTO);
}
