package ru.javawebinar.topjava.graduation.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.graduation.model.Poll;
import ru.javawebinar.topjava.graduation.repository.PollRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaPollRepository implements PollRepository {
    private static final Sort SORT_NAME = Sort.by(Sort.Direction.ASC, "name");

    private final CrudPollRepository crudPollRepository;
    private final CrudRestaurantRepository crudRestaurantRepository;
    private final CrudUserRepository crudUserRepository;

    public DataJpaPollRepository(CrudPollRepository crudPollRepository, CrudRestaurantRepository crudRestaurantRepository, CrudUserRepository crudUserRepository) {
        this.crudPollRepository = crudPollRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
        this.crudUserRepository = crudUserRepository;
    }

    @Override
    public Poll save(Poll poll, int restaurantId, int userId, LocalDateTime dateTime) {
        if (!poll.isNew() && get(poll.id(), userId, dateTime) == null) {
            return null;
        }
        poll.setRestaurant(crudRestaurantRepository.getReferenceById(restaurantId));
        poll.setUser(crudUserRepository.getReferenceById(userId));
        poll.setDateTime(dateTime);
        return crudPollRepository.save(poll);
    }

    @Override
    public boolean delete(int id, int userId, LocalDateTime dateTime) {
        return crudPollRepository.delete(userId, dateTime) != 0;
    }

    @Override
    public Poll get(int id, int userId, LocalDateTime dateTime) {
        return crudPollRepository.findById(id)
                .filter(poll -> poll.getUser().getId() == userId)
                .filter(poll -> poll.getDateTime().isEqual(dateTime))
                .orElse(null);
    }

    @Override
    public List<Poll> getPollByDate(LocalDateTime dateTime) {
        return crudPollRepository.getPollByDate(dateTime);
    }
}
