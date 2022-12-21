package ru.javawebinar.topjava.graduation.to;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.javawebinar.topjava.graduation.model.Restaurant;
import ru.javawebinar.topjava.graduation.model.User;

import java.beans.ConstructorProperties;
import java.time.LocalDateTime;
import java.util.Objects;

@ToString
@Getter
@Setter
public class PollTo extends BaseTo {
    private final LocalDateTime dateTime;
    private User user;
    private Restaurant restaurant;

    @ConstructorProperties({"id", "dateTime", "description", "calories", "excess"})
    public PollTo(Integer id, LocalDateTime dateTime, User user, Restaurant restaurant) {
        super(id);
        this.dateTime = dateTime;
        this.user = user;
        this.restaurant = restaurant;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateTime, user, restaurant);
    }
}
