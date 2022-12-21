package ru.javawebinar.topjava.graduation.repository;

import ru.javawebinar.topjava.graduation.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface MealRepository {
    // null if updated meal does not belong to userId
    Meal save(Meal meal, int restaurantId);

    // false if meal does not belong to userId
    boolean delete(int id, int restaurantId);

    // null if meal does not belong to userId
    Meal get(int id, int restaurantId);

    // ORDERED dateTime desc
    List<Meal> getAll(int restaurantId);

    // ORDERED dateTime desc
    List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int restaurantId);

    default Meal getWithRestaurant(int id, int restaurantId) {
        throw new UnsupportedOperationException();
    }
}
