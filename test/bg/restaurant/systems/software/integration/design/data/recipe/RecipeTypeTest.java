package bg.restaurant.systems.software.integration.design.data.recipe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecipeTypeTest {

    @Test
    public void testGetRecipeTypeString() {
        // Arrange
        RecipeType recipeType = RecipeType.SOUP;

        // Act
        String result = recipeType.getRecipeTypeString();

        // Assert
        assertEquals("soup", result);
    }

    @Test
    public void testGetRecipeType_ExistingType() {
        // Arrange
        String recipeTypeString = "main-course";

        // Act
        RecipeType result = RecipeType.getRecipeType(recipeTypeString);

        // Assert
        assertEquals(RecipeType.MAIN_COURSE, result);
    }

    @Test
    public void testGetRecipeType_UnknownType() {
        // Arrange
        String recipeTypeString = "unknown-type";

        // Act
        RecipeType result = RecipeType.getRecipeType(recipeTypeString);

        // Assert
        assertEquals(RecipeType.UNKNOWN, result);
    }
    
}