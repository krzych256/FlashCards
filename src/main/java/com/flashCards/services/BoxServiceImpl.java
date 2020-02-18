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
    public BoxDTO findById(long id) {
        return boxRepository.findById(id)
                .map(boxMapper::boxToBoxDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public List<BoxDTO> findAll() {
        return boxRepository.findAll()
                .stream()
                .map(boxMapper::boxToBoxDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BoxDTO save(BoxDTO boxDTO) {
        Box boxSaved = boxRepository.save(boxMapper.boxDTOToBox(boxDTO));
        return boxMapper.boxToBoxDTO(boxSaved);
    }

    @Override
    public void delete(BoxDTO boxDTO) {
        boxRepository.delete(boxMapper.boxDTOToBox(boxDTO));
    }
}
