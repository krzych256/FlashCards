package com.flashCards.services;

import com.flashCards.model.ProfileDTO;

import java.util.List;

public interface ProfileService {

    List<ProfileDTO> getAllProfiles();

    ProfileDTO getProfileById(Long id);

    ProfileDTO createNewProfile(ProfileDTO profileDTO);

    ProfileDTO updateOrCreateProfile(Long id, ProfileDTO profileDTO);

    void deleteById(Long id);
}
