package ru.javawebinar.topjava.graduation.service.datajpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.graduation.UserTestData;
import ru.javawebinar.topjava.graduation.service.AbstractUserServiceTest;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import static ru.javawebinar.topjava.Profiles.DATAJPA;

@ActiveProfiles(DATAJPA)
class DataJpaUserServiceTest extends AbstractUserServiceTest {
    @Test
    void getWithMeals() {
        User actual = service.getWithMeals(UserTestData.ADMIN_ID);
        UserTestData.USER_WITH_MEALS_MATCHER.assertMatch(actual, UserTestData.admin);
    }

    @Test
    void getWithMealsNotFound() {
        Assertions.assertThrows(NotFoundException.class,
                () -> service.getWithMeals(UserTestData.NOT_FOUND));
    }
}