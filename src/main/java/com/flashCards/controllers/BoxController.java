package com.flashCards.controllers;

import com.flashCards.model.BoxDTO;
import com.flashCards.services.BoxService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(BoxController.BASE_URL)
@ResponseStatus(HttpStatus.OK)
public class BoxController {

    public static final String BASE_URL = "/api/boxes";

    private final BoxService boxService;

    public BoxController(BoxService boxService) {
        this.boxService = boxService;
    }

    @GetMapping
    public List<BoxDTO> getAllBoxes() {
        return boxService.findAll();
    }

    @GetMapping("/{id}")
    public BoxDTO getBoxById(@PathVariable Long id) {
        return boxService.findById(id);
    }

    @PostMapping
    public BoxDTO createNewBox(@RequestBody BoxDTO boxDTO) {
        return boxService.save(boxDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteBox(@PathVariable Long id) {
        boxService.delete(boxService.findById(id));
    }
}
