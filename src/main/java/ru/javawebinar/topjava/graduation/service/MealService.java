package ru.javawebinar.topjava.graduation.service;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.javawebinar.topjava.graduation.model.Meal;
import ru.javawebinar.topjava.graduation.repository.MealRepository;

import java.time.LocalDate;
import java.util.List;

import static ru.javawebinar.topjava.graduation.util.DateTimeUtil.atStartOfDayOrMin;
import static ru.javawebinar.topjava.graduation.util.DateTimeUtil.atStartOfNextDayOrMax;
import static ru.javawebinar.topjava.graduation.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {

    private final MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal get(int id, int restaurantId) {
        return checkNotFoundWithId(repository.get(id, restaurantId), id);
    }

    public void delete(int id, int restaurantId) {
        checkNotFoundWithId(repository.delete(id, restaurantId), id);
    }

    public List<Meal> getBetweenInclusive(@Nullable LocalDate startDate, @Nullable LocalDate endDate, int restaurantId) {
        return repository.getBetweenHalfOpen(atStartOfDayOrMin(startDate), atStartOfNextDayOrMax(endDate), restaurantId);
    }

    public List<Meal> getAll(int restaurantId) {
        return repository.getAll(restaurantId);
    }

    public void update(Meal meal, int restaurantId) {
        Assert.notNull(meal, "meal must not be null");
        checkNotFoundWithId(repository.save(meal, restaurantId), meal.id());
    }

    public Meal create(Meal meal, int restaurantId) {
        Assert.notNull(meal, "meal must not be null");
        return repository.save(meal, restaurantId);
    }
}