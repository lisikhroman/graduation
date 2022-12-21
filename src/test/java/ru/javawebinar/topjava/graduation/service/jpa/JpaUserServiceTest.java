package ru.javawebinar.topjava.graduation.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.graduation.service.AbstractUserServiceTest;

import static ru.javawebinar.topjava.Profiles.JPA;

@ActiveProfiles(JPA)
class JpaUserServiceTest extends AbstractUserServiceTest {
}