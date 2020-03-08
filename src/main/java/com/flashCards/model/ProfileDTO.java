package com.flashCards.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProfileDTO {

    private Long id;
    private UserDTO userDTO;
    private List<BoxDTO> boxesDTO = new ArrayList<>();
}
