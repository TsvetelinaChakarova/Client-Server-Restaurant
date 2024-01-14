package bg.restaurant.systems.software.integration.design.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommandCreatorTest {
    @Test
    void testCommandCreatorWithNullCommand() {
        assertThrows(IllegalArgumentException.class, () -> CommandCreator.newCommand(null),
                "Command input cannot be null!");
    }

    @Test
    void testCommandCreatorWithValidCommand() {
        String clientInput = "createOrder 123";
        Command command = CommandCreator.newCommand(clientInput);
        assertNotNull(command);
        assertEquals("createOrder", command.command());
        assertEquals(1, command.arguments().length);
        assertEquals("123", command.arguments()[0]);
    }

    @Test
    void testCommandCreatorWithUnknownCommand() {
        String clientInput = "unknownCommand";
        Command command = CommandCreator.newCommand(clientInput);
        assertNotNull(command);
        assertEquals("Unknown", command.command());
        assertTrue(command.arguments().length == 0);
    }
}
