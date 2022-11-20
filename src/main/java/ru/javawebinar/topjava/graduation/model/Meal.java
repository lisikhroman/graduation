package ru.javawebinar.topjava.graduation.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@NamedQueries({
        @NamedQuery(name = Meal.ALL_SORTED, query = "SELECT m FROM Meal m WHERE m.restaurant.restaurantId=:restaurantId ORDER BY m.dateTime DESC"),
        @NamedQuery(name = Meal.DELETE, query = "DELETE FROM Meal m WHERE m.id=:id AND m.restaurant.restaurantId=:restaurantId"),
        @NamedQuery(name = Meal.GET_BETWEEN, query = """
                    SELECT m FROM Meal m 
                    WHERE m.restaurant.id=:restaurantId AND m.dateTime >= :startDateTime AND m.dateTime < :endDateTime ORDER BY m.dateTime DESC
                """),
//      @NamedQuery(name = Meal.UPDATE, query = "UPDATE Meal m SET m.dateTime = :datetime, m.calories= :calories," +
//                "m.description=:desc where m.id=:id and m.user.id=:userId")
})
@Entity
@Table(name = "restaurant_menus", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "date_time", "meal_name"}, name = "meals_unique_restaurants_datetime_idx")})
@ToString
@Getter
@Setter
public class Meal extends AbstractBaseEntity {
    public static final String ALL_SORTED = "Meal.getAll";
    public static final String DELETE = "Meal.delete";
    public static final String GET_BETWEEN = "Meal.getBetween";

    @Column(name = "date_time", nullable = false)
    @NotNull
    private LocalDateTime dateTime;

    @Column(name = "meal_name", nullable = false)
    @NotBlank
    @Size(min = 2, max = 120)
    private String mealName;

    @Column(name = "meal_price", nullable = false)
    @Range(min = 10, max = 5000)
    private int mealPrice;

    @Column(name = "restaurant_id", nullable = false)
    private int restaurantId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull
    @ToString.Exclude
    private Restaurant restaurant;

    public Meal() {
    }

    public Meal(LocalDateTime dateTime, String mealName, int mealPrice) {
        this(null, dateTime, mealName, mealPrice);
    }

    public Meal(Integer id, LocalDateTime dateTime, String mealName, int mealPrice) {
        super(id);
        this.dateTime = dateTime;
        this.mealName = mealName;
        this.mealPrice = mealPrice;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }
}
