package ru.javawebinar.topjava.graduation.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant r WHERE r.id=:id"),
        @NamedQuery(name = Restaurant.BY_NAME, query = "SELECT DISTINCT r FROM Restaurant r WHERE r.name=?1"),
        @NamedQuery(name = Restaurant.ALL_SORTED, query = "SELECT r FROM Restaurant r ORDER BY r.name"),
})
@Entity
@Table(name = "restaurants")
@ToString
@Getter
@Setter
public class Restaurant extends AbstractNamedEntity {

    public static final String DELETE = "Restaurant.delete";
    public static final String BY_NAME = "Restaurant.getByName";
    public static final String ALL_SORTED = "Restaurant.getAllSorted";

    @Column(name = "restaurant_id", nullable = false, unique = true)
    private int restaurantId;

    @Column(name = "name", nullable = false, unique = true)
    @NotBlank
    @Size(min = 2, max = 120)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")//, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OrderBy("dateTime DESC")
    @OnDelete(action = OnDeleteAction.CASCADE) //https://stackoverflow.com/a/44988100/548473
    @JsonManagedReference
    private List<Meal> meals;

    public Restaurant() {
    }

    public Restaurant(Restaurant r) {
        this(r.id, r.restaurantId, r.name);
    }

    public Restaurant(Integer id, int restaurantId, String name) {
        super(id, name);
        this.restaurantId = restaurantId;
    }
}