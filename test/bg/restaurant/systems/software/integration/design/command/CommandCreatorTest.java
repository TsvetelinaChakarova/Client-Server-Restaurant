package bg.restaurant.systems.software.integration.design.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommandCreatorTest {
    @Test
    void testCommandCreatorWithNullCommand() {
        assertEquals("Unknown", CommandCreator.newCommand(null).command(),
                "Command input cannot be null!");
    }

    @Test
    void testCommandCreatorWithValidCommand() {
        String clientInput = "get recipes --all";
        Command command = CommandCreator.newCommand(clientInput);
        assertNotNull(command);
        assertEquals("recipes", command.command());
        assertEquals(1, command.arguments().length);
        assertEquals("--all", command.arguments()[0]);
    }

    @Test
    void testCommandCreatorWithUnknownCommand() {
        String clientInput = "Unknown Command";
        Command command = CommandCreator.newCommand(clientInput);
        assertNotNull(command);
        assertEquals("Unknown", command.command());
        assertTrue(command.arguments().length == 1);
    }
}
