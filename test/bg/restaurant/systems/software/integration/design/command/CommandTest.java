package bg.restaurant.systems.software.integration.design.command;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;


public class CommandTest {
    private static Command command;

    @BeforeAll
    static void setUp() {
        command = new Command("command", new String[] {"arg1", "arg2"});
    }

    @Test
    void testCommandNameWithInvalidInput() {
        assertThrowsExactly(IllegalArgumentException.class, () -> new Command(null, new String[] {null}),
            "Command must not accept null or empty command name!");
        assertThrowsExactly(IllegalArgumentException.class, () -> new Command("", new String[] {null}),
            "Command must not accept null or empty command name!");
        assertThrowsExactly(IllegalArgumentException.class, () -> new Command(" ", new String[] {null}),
            "Command must not accept null or empty command name!");
    }

    @Test
    void testCommandArgsWithInvalidInput() {
        assertThrowsExactly(IllegalArgumentException.class, () -> new Command("null", null),
            "Command must not accept null command arguments!");
        assertThrowsExactly(IllegalArgumentException.class, () -> new Command("", new String[] {null}),
            "Command must not accept null or empty command arguments!");
        assertThrowsExactly(IllegalArgumentException.class, () -> new Command("", new String[] {""}),
            "Command must not accept null or empty command arguments!");
        assertThrowsExactly(IllegalArgumentException.class, () -> new Command("", new String[] {"   "}),
            "Command must not accept null or empty command arguments!");
        assertThrowsExactly(IllegalArgumentException.class, () -> new Command("", new String[] {"arg1", "       "}),
            "Command must not accept null or empty command arguments!");
    }
}
