package ru.geekbrains.mediator;

import java.util.Date;

public class ChatRoom implements ChatRoomMediator {

    @Override
    public void showMessage(User user, String message) {
        Date date = new Date();
        String sender = user.getName();

        System.out.printf("%s [%s]: %s", date, sender, message);
    }
}
