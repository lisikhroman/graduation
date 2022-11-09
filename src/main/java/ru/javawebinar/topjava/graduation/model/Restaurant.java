package ru.javawebinar.topjava.graduation.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@NamedQueries({
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant r WHERE r.id=:id"),
        @NamedQuery(name = Restaurant.ALL_SORTED, query = "SELECT u FROM User u LEFT JOIN FETCH u.roles ORDER BY u.name, u.email"),
})
@Entity
@Table(name = "restaurants")
@ToString
@Getter
@Setter
public class Restaurant extends AbstractNamedEntity {

    public static final String DELETE = "Restaurant.delete";
    public static final String ALL_SORTED = "Restaurant.getAllSorted";

    @Column(name = "restaurant_id", nullable = false, unique = true)
    private int restaurantId;

    @Column(name = "name", nullable = false)
    @NotBlank
    @Size(min = 2, max = 120)
    private String name;

    @Column(name = "date_time", nullable = false, columnDefinition = "timestamp default now()", updatable = false)
    @NotNull
    private Date dateTime = new Date();

    @Column(name = "number_of_votes", nullable = false)
    private int numberVotes;

    public Restaurant() {
    }

    public Restaurant(Restaurant r) {
        this(r.id, r.restaurantId, r.name, r.dateTime, r.numberVotes);
    }

    public Restaurant(Integer id, int restaurantId, String name, Date dateTime, int numberVotes) {
        super(id, name);
        this.restaurantId = restaurantId;
        this.dateTime = dateTime;
        this.numberVotes = numberVotes;
    }
}