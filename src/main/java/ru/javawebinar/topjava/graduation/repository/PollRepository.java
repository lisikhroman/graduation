package ru.javawebinar.topjava.graduation.repository;

import ru.javawebinar.topjava.graduation.model.Poll;

import java.time.LocalDateTime;
import java.util.List;

public interface PollRepository {
    Poll save(Poll poll, int restaurantId, int userId, LocalDateTime dateTime);

    boolean delete(int id, int userId, LocalDateTime dateTime);

    Poll get(int id, int userId, LocalDateTime dateTime);

    List<Poll> getPollByDate(LocalDateTime dateTime);

    default Poll getWithRestaurantUser(int id, int restaurantId, int userId) {
        throw new UnsupportedOperationException();
    }
}
