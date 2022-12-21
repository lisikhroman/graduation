package ru.javawebinar.topjava.graduation.to;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.javawebinar.topjava.graduation.HasId;

@ToString
@Getter
@Setter
public abstract class BaseTo implements HasId {
    protected Integer id;

    public BaseTo() {
    }

    public BaseTo(Integer id) {
        this.id = id;
    }
}
