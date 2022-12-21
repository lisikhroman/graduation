package ru.javawebinar.topjava.graduation.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import ru.javawebinar.topjava.graduation.util.DateTimeUtil;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NamedQueries({
        @NamedQuery(name = Poll.BY_NAME, query = "SELECT p FROM Poll p WHERE p.user.id=?1"),
        @NamedQuery(name = Poll.ALL_SORTED, query = "SELECT p FROM Poll p ORDER BY p.dateTime"),
        @NamedQuery(name = Poll.GET_BETWEEN, query = """
                    SELECT p FROM Poll p 
                    WHERE p.id=:pollId AND p.dateTime >= :startDateTime AND p.dateTime < :endDateTime ORDER BY p.dateTime DESC
                """),
})
@Entity
@Table(name = "polls")
@ToString
@Getter
@Setter
public class Poll extends AbstractBaseEntity {

    public static final String BY_NAME = "Poll.getByName";
    public static final String ALL_SORTED = "Poll.getAllSorted";
    public static final String GET_BETWEEN = "Poll.getBetween";

    @Column(name = "date_time", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    @NotNull(groups = View.Persist.class)
    @ToString.Exclude
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    @NotNull(groups = View.Persist.class)
    @ToString.Exclude
    private User user;

    public Poll() {
    }

    public Poll(Integer id, LocalDateTime dateTime, User user, Restaurant restaurant) {
        super(id);
        this.dateTime = dateTime;
        this.user = user;
        this.restaurant = restaurant;
    }
}