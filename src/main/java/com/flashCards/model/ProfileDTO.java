package com.flashCards.model;

import com.flashCards.domain.Box;
import com.flashCards.domain.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProfileDTO {

    private Long id;
    private User user;
    private List<Box> boxes = new ArrayList<>();
}
