package bg.restaurant.systems.software.integration.design.command;

import bg.restaurant.systems.software.integration.design.command.handlers.recipe.GetAllRecipesCommand;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import bg.restaurant.systems.software.integration.design.restaurant.RestaurantAPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetAllRecipesCommandTest {

 private GetAllRecipesCommand getAllRecipesCommand;
 private RestaurantAPI mockRestaurant;

 @BeforeEach
 public void setUp() {
  mockRestaurant = mock(RestaurantAPI.class);
  getAllRecipesCommand = new GetAllRecipesCommand(mockRestaurant);
 }

 @Test
 public void testExecute() throws SQLException {
  // Arrange
  String expectedOutput = "Mocked recipe data"; // Replace with your expected output

  // Mock the behavior of the RestaurantAPI
  when(mockRestaurant.getAllRecipes()).thenReturn(expectedOutput);

  // Act
  String actualOutput = getAllRecipesCommand.execute();

  // Assert
  assertEquals(expectedOutput, actualOutput);
 }
}