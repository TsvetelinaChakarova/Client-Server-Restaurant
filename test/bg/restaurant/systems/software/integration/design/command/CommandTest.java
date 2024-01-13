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
    void testCommandNameWithNull() {
        assertThrowsExactly(IllegalArgumentException.class, () -> new Command(null, new String[] {null}),
            "Command must not accept null in command!");
    }

    @Test
    void testCommandNameWithEmpty() {
        assertThrowsExactly(IllegalArgumentException.class, () -> new Command("", new String[] {null}),
            "Command must not accept null in command!");
    }

    @Test
    void testCommandNameWithBlank() {
        assertThrowsExactly(IllegalArgumentException.class, () -> new Command(" ", new String[] {null}),
            "Command must not accept null in command!");
    }

    @Test
    void testCommandArgsWithNull() {
        assertThrowsExactly(IllegalArgumentException.class, () -> new Command("null", null),
            "Command must not accept null in command!");
    }

    @Test
    void testAnyCommandArgWithNull() {
        assertThrowsExactly(IllegalArgumentException.class, () -> new Command("", new String[] {null}),
            "Command must not accept null in command!");
    }

    @Test
    void testAnyCommandArgWithEmpty() {
        assertThrowsExactly(IllegalArgumentException.class, () -> new Command("", new String[] {""}),
            "Command must not accept null in command!");
    }

    @Test
    void testAnyCommandArgWithBlank() {
        assertThrowsExactly(IllegalArgumentException.class, () -> new Command("", new String[] {"   "}),
            "Command must not accept null in command!");
    }

    @Test
    void testMAnyCommandArgWithBlank() {
        assertThrowsExactly(IllegalArgumentException.class, () -> new Command("", new String[] {"arg1", "       "}),
            "Command must not accept null in command!");
    }

}
