package com.flashCards.controllers;

import com.flashCards.model.BoxDTO;
import com.flashCards.services.BoxService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(BoxController.BASE_URL)
public class BoxController {

    public static final String BASE_URL = "/api/boxes";

    private final BoxService boxService;

    public BoxController(BoxService boxService) {
        this.boxService = boxService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BoxDTO> getAllBoxes() {
        return boxService.getAllBoxes();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BoxDTO getBoxById(@PathVariable Long id) {
        return boxService.getBoxById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BoxDTO createNewBox(@RequestBody BoxDTO boxDTO) {
        return boxService.createNewBox(boxDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BoxDTO updateBox(@PathVariable Long id, @RequestBody BoxDTO boxDTO) {
        return boxService.updateOrCreateBox(id, boxDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteBox(@PathVariable Long id) {
        boxService.deleteById(id);
    }
}
