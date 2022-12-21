package ru.javawebinar.topjava.graduation.repository.datajpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.graduation.model.Meal;
import ru.javawebinar.topjava.graduation.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaMealRepository implements MealRepository {

    private final CrudMealRepository crudMealRepository;

    private final CrudRestaurantRepository crudRestaurantRepository;

    public DataJpaMealRepository(CrudMealRepository crudMealRepository, CrudUserRepository crudUserRepository) {
        this.crudMealRepository = crudMealRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Override
    @Transactional
    public Meal save(Meal meal, int restaurantId) {
        if (!meal.isNew() && get(meal.id(), restaurantId) == null) {
            return null;
        }
        meal.setRestaurant(crudRestaurantRepository.getReferenceById(restaurantId));
        return crudMealRepository.save(meal);
    }

    @Override
    public boolean delete(int id, int restaurantId) {
        return crudMealRepository.delete(id, restaurantId) != 0;
    }

    @Override
    public Meal get(int id, int restaurantId) {
        return crudMealRepository.findById(id)
                .filter(meal -> meal.getRestaurant().getId() == restaurantId)
                .orElse(null);
    }

    @Override
    public List<Meal> getAll(int restaurantId) {
        return crudMealRepository.getAll(restaurantId);
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int restaurantId) {
        return crudMealRepository.getBetweenHalfOpen(startDateTime, endDateTime, restaurantId);
    }

    @Override
    public Meal getWithRestaurant(int id, int restaurantId) {
        return crudMealRepository.getWithUser(id, restaurantId);
    }
}
