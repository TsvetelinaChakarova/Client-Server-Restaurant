package bg.restaurant.systems.software.integration.design.command;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CommandExecutorTest {
    @Mock
    private static CommandExecutor commandExecutor;

    @Mock
    private static CommandCreator commandCreator;

    @BeforeAll
    static void setUp() {
        commandExecutor = Mockito.mock(CommandExecutor.class);
        commandCreator = Mockito.mock(CommandCreator.class);
    }

    @Test
    void testGetAllRecipes() {
        when(commandExecutor.execute(any(Command.class))).thenReturn("Recipes");

        assertEquals("Recipes", commandExecutor.execute(CommandCreator.newCommand("get recipes --all")),
            "The get all recipes command must return correctly data");
    }


}
