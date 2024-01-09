package bg.restaurant.systems.software.integration.design.command;

import org.junit.jupiter.api.BeforeAll;

public class CommandExecutorTest {
    private static CommandExecutor commandExecutor;

    @BeforeAll
    static void setUp() {
        commandExecutor = new CommandExecutor(null);
    }
}
