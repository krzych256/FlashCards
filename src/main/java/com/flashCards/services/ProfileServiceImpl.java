package com.flashCards.services;

import com.flashCards.domain.Profile;
import com.flashCards.exceptions.ResourceNotFoundException;
import com.flashCards.mapper.ProfileMapper;
import com.flashCards.model.ProfileDTO;
import com.flashCards.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileMapper profileMapper;
    private final ProfileRepository profileRepository;

    public ProfileServiceImpl(ProfileMapper profileMapper,
                              ProfileRepository profileRepository) {
        this.profileMapper = profileMapper;
        this.profileRepository = profileRepository;
    }

    @Override
    public List<ProfileDTO> getAllProfiles() {

        return profileRepository.findAll()
                .stream()
                .map(profileMapper::profileToProfileDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProfileDTO getProfileById(Long id) {
        return profileRepository.findById(id)
                .map(profileMapper::profileToProfileDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public ProfileDTO createNewProfile(ProfileDTO profileDTO) {

        Profile profile = profileMapper.profileDTOToProfile(profileDTO);
        Profile savedProfile = profileRepository.save(profile);
        return profileMapper.profileToProfileDTO(savedProfile);
    }

    @Override
    public ProfileDTO updateOrCreateProfile(Long id, ProfileDTO profileDTO) {
        return profileMapper.profileToProfileDTO(profileRepository.findById(id)
                        .map(profile -> {
//                    profile.setUser(profileDTO.getUserDTO());
                            return profileRepository.save(profile);
                        })
                        .orElseGet(() -> {
                            profileDTO.setId(id);
                            return profileRepository.save(profileMapper.profileDTOToProfile(profileDTO));
                        })
        );
    }

    @Override
    public void deleteById(Long id) {
        profileRepository.deleteById(id);
    }
}
