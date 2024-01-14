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
        assertEquals("createOrder", command.getCommandName());
        assertEquals(1, command.getArguments().size());
        assertEquals("123", command.getArguments().get(0));
    }

    @Test
    void testCommandCreatorWithUnknownCommand() {
        String clientInput = "unknownCommand";
        Command command = CommandCreator.newCommand(clientInput);
        assertNotNull(command);
        assertEquals("Unknown", command.getCommandName());
        assertTrue(command.getArguments().isEmpty());
    }
}
