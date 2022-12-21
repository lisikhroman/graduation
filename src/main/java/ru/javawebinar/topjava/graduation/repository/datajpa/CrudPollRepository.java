package ru.javawebinar.topjava.graduation.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.graduation.model.Poll;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudPollRepository extends JpaRepository<Poll, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Poll p WHERE p.user.id=:userId AND p.dateTime=:dateTime")
    int delete(@Param("userId") int userId, @Param("dateTime") LocalDateTime dateTime);

    @Query("SELECT p.restaurant.name, count(p.id) FROM Poll p WHERE p.dateTime =: dateTime GROUP BY p.restaurant.restaurantId ORDER BY count(p.id) DESC")
    List<Poll> getPollByDate(@Param("dateTime") LocalDateTime dateTime);
}
