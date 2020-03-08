package com.flashCards.services;

import com.flashCards.domain.FlashCard;
import com.flashCards.exceptions.ResourceNotFoundException;
import com.flashCards.mapper.FlashCardMapper;
import com.flashCards.model.FlashCardDTO;
import com.flashCards.repositories.FlashCardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlashCardServiceImpl implements FlashCardService {
    
    private final FlashCardMapper flashCardMapper;
    private final FlashCardRepository flashCardRepository;

    public FlashCardServiceImpl(FlashCardMapper flashCardMapper, 
                                FlashCardRepository flashCardRepository) {
        this.flashCardMapper = flashCardMapper;
        this.flashCardRepository = flashCardRepository;
    }

    @Override
    public List<FlashCardDTO> getAllFlashCards() {
        return flashCardRepository.findAll()
                .stream()
                .map(flashCardMapper::flashCardToFlashCardDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FlashCardDTO getFlashCardById(Long id) {
        return flashCardRepository.findById(id)
                .map(flashCardMapper::flashCardToFlashCardDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public FlashCardDTO createNewFlashCard(FlashCardDTO flashCardDTO) {
        FlashCard flashCard = flashCardMapper.flashCardDTOToFlashCard(flashCardDTO);
        FlashCard savedFlashCard = flashCardRepository.save(flashCard);
        return flashCardMapper.flashCardToFlashCardDTO(savedFlashCard);
    }

    @Override
    public FlashCardDTO updateOrCreateFlashCard(Long id, FlashCardDTO flashCardDTO) {
        return flashCardMapper.flashCardToFlashCardDTO(flashCardRepository.findById(id)
                .map(flashCard -> {
                    flashCard.setLevel(flashCardDTO.getLevel());
                    flashCard.setFront(flashCardDTO.getFront());
                    flashCard.setBack(flashCardDTO.getBack());
                    flashCard.setDescription(flashCardDTO.getDescription());
//                    flashCard.setCategory(flashCardDTO.getCategory());
                    return flashCardRepository.save(flashCard);
                })
                .orElseGet(() -> {
                    flashCardDTO.setId(id);
                    return flashCardRepository.save(flashCardMapper.flashCardDTOToFlashCard(flashCardDTO));
                })
        );
    }

    @Override
    public void deleteById(Long id) {
        flashCardRepository.deleteById(id);
    }
}
