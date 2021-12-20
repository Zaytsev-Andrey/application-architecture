package ru.geekbrains.mediator;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MediatorTest {

    private ByteArrayOutputStream out;

    private static ChatRoomMediator mediator;

    @BeforeAll
    public static void init() {
        mediator = new ChatRoom();
    }

    @BeforeEach
    public void initMethod() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @Test
    public void sendMessageTest() {
        User user = new User("Username", mediator);
        user.send("Hi!");
        Assertions.assertTrue(out.toString().contains("[Username]: Hi!"));
    }

    @AfterEach
    public void completeMethod() {
        System.setOut(System.out);
    }
}
