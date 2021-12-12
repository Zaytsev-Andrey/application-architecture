package ru.geekbrains.network.session;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Реализация SessionRepository, для хранения сессий используется HashMap
 */
public class SimpleSessionRepository implements SessionRepository {

    private Map<UUID, Session> sessions = new HashMap<>();

    @Override
    public Optional<Session> findById(UUID id) {
        return Optional.of(sessions.get(id));
    }

    @Override
    public void save(Session session) {
        sessions.put(session.getId(), session);
    }
}
