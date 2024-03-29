package bg.restaurant.systems.software.integration.design.data.recipe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecipeTypeTest {

    @Test
    public void testGetRecipeTypeString() {
        RecipeType recipeType = RecipeType.SOUP;

        String result = recipeType.getRecipeTypeString();

        assertEquals("soup", result);
    }

    @Test
    public void testGetRecipeTypeExistingType() {
        String recipeTypeString = "main-course";

        RecipeType result = RecipeType.getRecipeType(recipeTypeString);

        assertEquals(RecipeType.UNKNOWN, result);
    }

    @Test
    public void testGetRecipeTypeUnknownType() {
        String recipeTypeString = "unknown-type";

        RecipeType result = RecipeType.getRecipeType(recipeTypeString);

        assertEquals(RecipeType.UNKNOWN, result);
    }
}