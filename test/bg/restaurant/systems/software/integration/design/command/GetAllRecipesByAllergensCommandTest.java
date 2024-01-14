package bg.restaurant.systems.software.integration.design.command;

import bg.restaurant.systems.software.integration.design.command.handlers.recipe.GetAllRecipesByAllergensCommand;
import bg.restaurant.systems.software.integration.design.restaurant.RestaurantAPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetAllRecipesByAllergensCommandTest {
 private RestaurantAPI restaurant;
 private GetAllRecipesByAllergensCommand command;

 @BeforeEach
 public void setUp() {
  restaurant = mock(RestaurantAPI.class);
  command = new GetAllRecipesByAllergensCommand(restaurant, new String[]{"allergen1", "allergen2"});
 }

 @Test
 public void testExecute() throws SQLException {
  // Mock the behavior of the RestaurantAPI
  // when calling the method to get recipes by allergens
  when(restaurant.getAllRecipesByAllergens(List.of(new String[]{"allergen1", "allergen2"})))
    .thenReturn("recipe1, recipe2");

  // Execute the command
  String result = command.execute();

  // Verify the result
  assertEquals("recipe1, recipe2", result);
 }
}