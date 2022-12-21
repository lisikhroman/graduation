package ru.javawebinar.topjava.graduation.web.json;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.graduation.MealTestData;
import ru.javawebinar.topjava.graduation.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonUtilTest {
    private static final Logger log = LoggerFactory.getLogger(JsonUtilTest.class);

    @Test
    void readWriteValue() {
        String json = JsonUtil.writeValue(MealTestData.adminMeal1);
        log.info(json);
        Meal meal = JsonUtil.readValue(json, Meal.class);
        MealTestData.MEAL_MATCHER.assertMatch(meal, MealTestData.adminMeal1);
    }

    @Test
    void readWriteValues() {
        String json = JsonUtil.writeValue(MealTestData.meals);
        log.info(json);
        List<Meal> actual = JsonUtil.readValues(json, Meal.class);
        MealTestData.MEAL_MATCHER.assertMatch(actual, MealTestData.meals);
    }

    @Test
    void writeOnlyAccess() {
        String json = JsonUtil.writeValue(UserTestData.user);
        System.out.println(json);
        assertThat(json, not(containsString("password")));
        String jsonWithPass = UserTestData.jsonWithPassword(UserTestData.user, "newPass");
        System.out.println(jsonWithPass);
        User user = JsonUtil.readValue(jsonWithPass, User.class);
        assertEquals(user.getPassword(), "newPass");
    }
}