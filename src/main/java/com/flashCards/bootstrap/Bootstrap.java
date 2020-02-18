package com.flashCards.bootstrap;

import com.flashCards.domain.Box;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        loadBoxes();
    }

    private void loadBoxes() {

    }
}
