package ru.javawebinar.topjava.graduation.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.javawebinar.topjava.graduation.model.Poll;
import ru.javawebinar.topjava.graduation.repository.PollRepository;

import java.time.LocalDateTime;
import java.util.List;

import static ru.javawebinar.topjava.graduation.util.ValidationUtil.checkNotFoundWithId;

@Service
public class PollService {

    private final PollRepository repository;

    public PollService(PollRepository repository) {
        this.repository = repository;
    }

    public Poll get(int id, int userId, LocalDateTime dateTime) {
        return checkNotFoundWithId(repository.get(id, userId, dateTime), id);
    }

    public void delete(int id, int userId, LocalDateTime dateTime) {
        checkNotFoundWithId(repository.delete(id, userId, dateTime), id);
    }

    public List<Poll> getPollByDate(LocalDateTime dateTime) {
        return repository.getPollByDate(dateTime);
    }

    public void update(Poll poll, int restaurantId, int userId, LocalDateTime dateTime) {
        Assert.notNull(poll, "poll must not be null");
        checkNotFoundWithId(repository.save(poll, restaurantId, userId, dateTime), poll.id());
    }

    public Poll create(Poll poll, int restaurantId, int userId, LocalDateTime dateTime) {
        Assert.notNull(poll, "poll must not be null");
        return repository.save(poll, restaurantId, userId, dateTime);
    }
}