package bg.restaurant.systems.software.integration.design.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class CommandCreatorTest {
    @Test
    void testCommandCreatorWithNullCommand() {
        assertThrowsExactly(IllegalArgumentException.class, () -> CommandCreator.newCommand(null),
            "Command input cannot be null!");
    }
}
