package com.flashCards.controllers;

import com.flashCards.model.FlashCardDTO;
import com.flashCards.services.FlashCardService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(FlashCardController.BASE_URL)
public class FlashCardController {

    public static final String BASE_URL = "/api/flash-card";

    private final FlashCardService flashCardService;

    public FlashCardController(FlashCardService flashCardService) {
        this.flashCardService = flashCardService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FlashCardDTO> getAllFlashCards() {
        return flashCardService.getAllFlashCards();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FlashCardDTO getFlashCardById(@PathVariable Long id) {
        return flashCardService.getFlashCardById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FlashCardDTO createNewFlashCard(@RequestBody FlashCardDTO flashCardDTO) {
        return flashCardService.createNewFlashCard(flashCardDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FlashCardDTO updateFlashCard(@PathVariable Long id, @RequestBody FlashCardDTO flashCardDTO) {
        return flashCardService.updateOrCreateFlashCard(id, flashCardDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFlashCard(@PathVariable Long id) {
        flashCardService.deleteById(id);
    }
}
