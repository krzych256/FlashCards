package com.flashCards.controllers;

import com.flashCards.model.ProfileDTO;
import com.flashCards.services.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ProfileController.BASE_URL)
public class ProfileController {

    public static final String BASE_URL = "/api/profile";

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProfileDTO> getAllCategories() {
        return profileService.getAllProfiles();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProfileDTO getProfileById(@PathVariable Long id) {
        return profileService.getProfileById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProfileDTO createNewProfile(@RequestBody ProfileDTO profileDTO) {
        return profileService.createNewProfile(profileDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProfileDTO updateProfile(@PathVariable Long id, @RequestBody ProfileDTO profileDTO) {
        return profileService.updateOrCreateProfile(id, profileDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProfile(@PathVariable Long id) {
        profileService.deleteById(id);
    }
}
