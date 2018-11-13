package guru.springframework.converters;

import guru.springframework.command.RecipeCommand;
import guru.springframework.domain.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class RecipeToRecipeCommandTest {

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

    private RecipeToRecipeCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new RecipeToRecipeCommand(new CategoryToCategoryCommand(),
                new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
                new NotesToNotesCommand());
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Recipe()));
    }

    @Test
    public void convert() throws Exception {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(RECIPE_ID);
        recipe.setCookTime(COOK_TIME);
        recipe.setPrepTime(PREP_TIME);
        recipe.setDescription(DESCRIPTION);
        recipe.setDifficulty(DIFFICULTY);
        recipe.setDirections(DIRECTIONS);
        recipe.setServings(SERVINGS);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);

        Notes notes = new Notes();
        notes.setId(NOTES_ID);
        recipe.setNotes(notes);

        Category cat1 = new Category();
        cat1.setId(CAT_ID_1);

        Category cat2 = new Category();
        cat2.setId(CAT_ID_2);
        Set<Category> categories = new HashSet<>();
        categories.add(cat1);
        categories.add(cat2);

        recipe.setCategories(categories);

        Ingredient ing1 = new Ingredient();
        ing1.setId(INGREDIENT_ID_1);

        Ingredient ing2 = new Ingredient();
        ing2.setId(INGREDIENT_ID_2);

        Ingredient ing3 = new Ingredient();
        ing3.setId(INGREDIENT_ID_3);

        recipe.addIngredient(ing1).addIngredient(ing2).addIngredient(ing3);

        //when
        RecipeCommand command = converter.convert(recipe);

        //then
        assertNotNull(command);
        assertEquals(RECIPE_ID, command.getId());
        assertEquals(COOK_TIME, command.getCookTime());
        assertEquals(PREP_TIME, command.getPrepTime());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(DIFFICULTY, command.getDifficulty());
        assertEquals(DIRECTIONS, command.getDirections());
        assertEquals(SERVINGS, command.getServings());
        assertEquals(SOURCE, command.getSource());
        assertEquals(URL, command.getUrl());
        assertEquals(NOTES_ID, command.getNotes().getId());
        assertEquals(2, command.getCategories().size());
        assertEquals(3, command.getIngredients().size());

    }
}