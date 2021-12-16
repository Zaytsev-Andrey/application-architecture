package ru.geekbrains.network.session;

public class SessionFactory {

    public static Session create() {
        Session session = new HttpSession(null);
        session.getNewCookies().add("SESSIONID", session.getId().toString());
        return session;
    }
}
