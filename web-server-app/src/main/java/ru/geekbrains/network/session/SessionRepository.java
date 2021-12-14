package ru.geekbrains.network.session;

import java.util.Optional;
import java.util.UUID;

public interface SessionRepository {

    Optional<Session> findById(UUID id);

    Session save(Session session);
}
