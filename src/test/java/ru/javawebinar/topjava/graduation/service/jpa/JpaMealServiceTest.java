package ru.javawebinar.topjava.graduation.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.graduation.service.AbstractMealServiceTest;

import static ru.javawebinar.topjava.Profiles.JPA;

@ActiveProfiles(JPA)
class JpaMealServiceTest extends AbstractMealServiceTest {
}