package guru.springframework.converters;

import guru.springframework.command.CategoryCommand;
import guru.springframework.command.IngredientCommand;
import guru.springframework.command.NotesCommand;
import guru.springframework.command.RecipeCommand;
import guru.springframework.domain.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class RecipeCommandToRecipeTest {

    private static Long RECIPE_ID = 1L;
    private static Integer COOK_TIME = Integer.valueOf("7");
    private static Integer PREP_TIME = Integer.valueOf("10");
    private static Integer SERVINGS = Integer.valueOf("4");
    private static String DESCRIPTION = "Description";
    private static String DIRECTIONS = "Directions";
    private static String SOURCE = "Source";
    private static String URL = "Url";
    private static Long CAT_ID_1 = 1L;
    private static Long CAT_ID_2 = 2L;
    private static Long INGREDIENT_ID_1 = 1L;
    private static Long INGREDIENT_ID_2 = 2L;
    private static Long INGREDIENT_ID_3 = 3L;
    private static Long NOTES_ID = 1L;
    private static Difficulty DIFFICULTY = Difficulty.EASY;

    private RecipeCommandToRecipe converter;

    @Before
    public void setUp() throws Exception {
        converter = new RecipeCommandToRecipe(new CategoryCommandToCategory(),
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new NotesCommandToNotes());
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new RecipeCommand()));
    }

    @Test
    public void convert() throws Exception {
        //given
        RecipeCommand command = new RecipeCommand();
        command.setId(RECIPE_ID);
        command.setCookTime(COOK_TIME);
        command.setPrepTime(PREP_TIME);
        command.setDescription(DESCRIPTION);
        command.setDifficulty(DIFFICULTY);
        command.setDirections(DIRECTIONS);
        command.setServings(SERVINGS);
        command.setSource(SOURCE);
        command.setUrl(URL);

        NotesCommand notes = new NotesCommand();
        notes.setId(NOTES_ID);
        command.setNotes(notes);

        CategoryCommand cat1 = new CategoryCommand();
        cat1.setId(CAT_ID_1);

        CategoryCommand cat2 = new CategoryCommand();
        cat2.setId(CAT_ID_2);
        Set<CategoryCommand> categories = new HashSet<>();
        categories.add(cat1);
        categories.add(cat2);

        command.setCategories(categories);

        IngredientCommand ing1 = new IngredientCommand();
        ing1.setId(INGREDIENT_ID_1);

        IngredientCommand ing2 = new IngredientCommand();
        ing2.setId(INGREDIENT_ID_2);

        IngredientCommand ing3 = new IngredientCommand();
        ing3.setId(INGREDIENT_ID_3);
        Set<IngredientCommand> ingredients = new HashSet<>();
        ingredients.add(ing1);
        ingredients.add(ing2);
        ingredients.add(ing3);

        command.setIngredients(ingredients);

        //when
        Recipe recipe = converter.convert(command);

        //then
        assertNotNull(recipe);
        assertEquals(RECIPE_ID, recipe.getId());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(NOTES_ID, recipe.getNotes().getId());
        assertEquals(2, recipe.getCategories().size());
        assertEquals(3, recipe.getIngredients().size());
    }
}