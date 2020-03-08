package com.flashCards.bootstrap;

import com.flashCards.domain.Box;
import com.flashCards.domain.Category;
import com.flashCards.domain.FlashCard;
import com.flashCards.domain.Profile;
import com.flashCards.domain.auth.User;
import com.flashCards.repositories.*;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final BoxRepository boxRepository;
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final CategoryRepository categoryRepository;
    private final FlashCardRepository flashCardRepository;

    public Bootstrap(BoxRepository boxRepository,
                     UserRepository userRepository,
                     ProfileRepository profileRepository,
                     CategoryRepository categoryRepository,
                     FlashCardRepository flashCardRepository) {
        this.boxRepository = boxRepository;
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.categoryRepository = categoryRepository;
        this.flashCardRepository = flashCardRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        insertUsers();
        insertCategories();
        insertFlashCards();
        insertBoxes();
        insertProfiles();
    }

    private void insertUsers() {

        User users = new User();
        users.setUserName("John");
        users.setEmail("john@email.com");
        users.setPassword("password");
        users.setEnabled(true);

        User user2 = new User();
        user2.setUserName("Mike");
        user2.setEmail("mike@email.com");
        user2.setPassword("password2");
        user2.setEnabled(true);

        userRepository.save(users);
        userRepository.save(user2);
    }

    private void insertCategories() {

        Category category = new Category();
        category.setId(1L);
        category.setName("English");

        Category category2 = new Category();
        category2.setId(2L);
        category2.setName("Sports");

        categoryRepository.save(category);
        categoryRepository.save(category2);
    }

    private void insertFlashCards() {

        Category category = categoryRepository.findById(1L).orElse(null);

        FlashCard flashCard = new FlashCard();
        flashCard.setId(1L);
        flashCard.setLevel("1");
        flashCard.setFront("Pies");
        flashCard.setBack("Dog");
        flashCard.setDescription("Opis Pies");
        flashCard.setCategory(category);

        FlashCard flashCard2 = new FlashCard();
        flashCard2.setId(2L);
        flashCard2.setLevel("1");
        flashCard2.setFront("Kot");
        flashCard2.setBack("Cat");
        flashCard2.setDescription("Opis Kot");
        flashCard2.setCategory(category);

        flashCardRepository.save(flashCard);
        flashCardRepository.save(flashCard2);
    }

    private void insertBoxes() {

        Category category = categoryRepository.findById(1L).orElse(null);
        Category category2 = categoryRepository.findById(2L).orElse(null);

        FlashCard flashCard = flashCardRepository.findById(1L).orElse(null);
        FlashCard flashCard2 = flashCardRepository.findById(2L).orElse(null);

        Box box = new Box();
        box.setId(1L);
        box.setName("Box 01");
        box.setCategory(category);
        box.setFlashCards(Arrays.asList(flashCard, flashCard2));

        Box box2 = new Box();
        box2.setId(2L);
        box2.setName("Box 02");
        box2.setCategory(category2);
        box2.setFlashCards(null);

        boxRepository.save(box);
        boxRepository.save(box2);
    }

    private void insertProfiles() {

        User user = userRepository.findById(1L).orElse(null);

        Box box = boxRepository.findById(1L).orElse(null);

        Profile profile = new Profile();
        profile.setId(1L);
        profile.setUser(user);
        profile.setBoxes(Arrays.asList(box));

        profileRepository.save(profile);
    }
}
