package ru.javawebinar.topjava.graduation.service.datajpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.graduation.MealTestData;
import ru.javawebinar.topjava.graduation.UserTestData;
import ru.javawebinar.topjava.graduation.service.AbstractMealServiceTest;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import static ru.javawebinar.topjava.Profiles.DATAJPA;

@ActiveProfiles(DATAJPA)
class DataJpaMealServiceTest extends AbstractMealServiceTest {
    @Test
    void getWithUser() {
        Meal adminMeal = service.getWithUser(MealTestData.ADMIN_MEAL_ID, UserTestData.ADMIN_ID);
        MealTestData.MEAL_MATCHER.assertMatch(adminMeal, MealTestData.adminMeal1);
        UserTestData.USER_MATCHER.assertMatch(adminMeal.getUser(), UserTestData.admin);
    }

    @Test
    void getWithUserNotFound() {
        Assertions.assertThrows(NotFoundException.class,
                () -> service.getWithUser(MealTestData.NOT_FOUND, UserTestData.ADMIN_ID));
    }
}
