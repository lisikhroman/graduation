package ru.javawebinar.topjava.graduation.to;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.beans.ConstructorProperties;
import java.time.LocalDateTime;
import java.util.Objects;

@ToString
@Getter
@Setter
public class RestaurantTo extends BaseTo {
    private final LocalDateTime dateTime;
    private final String mealName;
    private final int mealPrice;

    @ConstructorProperties({"id", "dateTime", "description", "calories", "excess"})
    public RestaurantTo(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.mealName = description;
        this.mealPrice = calories;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateTime, mealName, mealPrice);
    }
}
