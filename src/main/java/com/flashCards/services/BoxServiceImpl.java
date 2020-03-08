package com.flashCards.services;

import com.flashCards.domain.Box;
import com.flashCards.exceptions.ResourceNotFoundException;
import com.flashCards.mapper.BoxMapper;
import com.flashCards.model.BoxDTO;
import com.flashCards.repositories.BoxRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoxServiceImpl implements BoxService {

    private final BoxMapper boxMapper;
    private final BoxRepository boxRepository;

    public BoxServiceImpl(BoxMapper boxMapper, BoxRepository boxRepository) {
        this.boxMapper = boxMapper;
        this.boxRepository = boxRepository;
    }

    @Override
    public List<BoxDTO> getAllBoxes() {

        return boxRepository.findAll()
                .stream()
                .map(boxMapper::boxToBoxDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BoxDTO getBoxById(Long id) {

        return boxRepository.findById(id)
                .map(boxMapper::boxToBoxDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public BoxDTO createNewBox(BoxDTO boxDTO) {

        Box box = boxMapper.boxDTOToBox(boxDTO);
        Box savedBox = boxRepository.save(box);
        return boxMapper.boxToBoxDTO(savedBox);
    }

    @Override
    public BoxDTO updateOrCreateBox(Long id, BoxDTO boxDTO) {

        return boxMapper.boxToBoxDTO(boxRepository.findById(id)
                        .map(box -> {
                            box.setName(boxDTO.getName());
//                    box.setCategory(boxDTO.getCategory());
//                    box.setFlashCards(boxDTO.getFlashCardsDTO());
                            return boxRepository.save(box);
                        })
                        .orElseGet(() -> {
                            boxDTO.setId(id);
                            return boxRepository.save(boxMapper.boxDTOToBox(boxDTO));
                        })
        );
    }

    @Override
    public void deleteById(Long id) {
        boxRepository.deleteById(id);
    }
}
