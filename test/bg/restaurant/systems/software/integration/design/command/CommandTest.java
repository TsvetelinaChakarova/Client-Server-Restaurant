package bg.restaurant.systems.software.integration.design.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    public void testValidCommand() {
        String command = "execute";
        String[] arguments = {"arg1", "arg2"};

        Command cmd = new Command(command, arguments);

        Assertions.assertEquals(command, cmd.command());
        Assertions.assertArrayEquals(arguments, cmd.arguments());
    }

    @Test
    public void testNullCommand() {
        String[] arguments = {"arg1", "arg2"};

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Command(null, arguments);
        });
    }

    @Test
    public void testEmptyCommand() {
        String command = "";
        String[] arguments = {"arg1", "arg2"};

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Command(command, arguments);
        });
    }

    @Test
    public void testBlankCommand() {
        String command = "   ";
        String[] arguments = {"arg1", "arg2"};

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Command(command, arguments);
        });
    }

    @Test
    public void testNullArguments() {
        String command = "execute";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Command(command, null);
        });
    }

    @Test
    public void testNullArgument() {
        String command = "execute";
        String[] arguments = {"arg1", null, "arg3"};

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Command(command, arguments);
        });
    }

    @Test
    public void testEmptyArgument() {
        String command = "execute";
        String[] arguments = {"arg1", "", "arg3"};

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Command(command, arguments);
        });
    }

    @Test
    public void testBlankArgument() {
        String command = "execute";
        String[] arguments = {"arg1", "   ", "arg3"};

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Command(command, arguments);
        });
    }


    @Test
    public void testCreationCommandWithOf() {
        Command testCommand = Command.of("one", new String[]{"two, three"});

        assertEquals(testCommand.command(), "one");
    }
}
