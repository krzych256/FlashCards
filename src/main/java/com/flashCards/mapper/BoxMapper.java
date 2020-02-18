package com.flashCards.mapper;

import com.flashCards.domain.Box;
import com.flashCards.model.BoxDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BoxMapper {

    BoxMapper INSTANCE = Mappers.getMapper(BoxMapper.class);

    BoxDTO boxToBoxDTO(Box box);

    @Mapping( target = "profile", ignore = true)
    Box boxDTOToBox(BoxDTO boxDTO);
}
