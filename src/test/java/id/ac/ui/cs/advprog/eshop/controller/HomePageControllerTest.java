package id.ac.ui.cs.advprog.eshop.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HomePageControllerTest {

    private final HomePageController homePageController = new HomePageController();

    @Test
    void homePageShouldReturnHomePageViewName() {
        String viewName = homePageController.homePage();

        assertEquals("homePage", viewName);
    }
}
