package guru.springframework.bootstrap;

import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Note;
import guru.springframework.domain.Recipe;
import guru.springframework.repository.CategoryRepository;
import guru.springframework.repository.IngredientRepository;
import guru.springframework.repository.RecipeRepository;
import guru.springframework.repository.UnitOfMeasureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataLoader implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;
    private RecipeRepository recipeRepository;
    private IngredientRepository ingredientRepository;

    public DataLoader(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository,
                      RecipeRepository recipeRepository, IngredientRepository ingredientRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {

        Ingredient avocado = new Ingredient("ripe avocados", new BigDecimal(2), this.unitOfMeasureRepository.findByDescription("").get());
        Ingredient salt = new Ingredient("salt", new BigDecimal(.5), this.unitOfMeasureRepository.findByDescription("Teaspoon").get());
        Ingredient lime = new Ingredient("fresh lime juice or lemon juice", new BigDecimal(.5), this.unitOfMeasureRepository.findByDescription("Tablespoon").get());

        Note note = new Note("The BEST guacamole! So easy to make with ripe avocados, salt, serrano " +
                " chiles,cilantro and lime. Garnish with red radishes or jicama. Serve with tortilla chips. " +
                "Watch how to make guacamole - it's easy!");

        Recipe recipe = new Recipe();

        recipe.setNote(note);
        recipe.setDescription("Perfect Guacamole!");
        recipe.setCookTime(0);
        recipe.setPrepTime(10);
        recipe.addIngredient(avocado)
                .addIngredient(salt)
                .addIngredient(lime);
        recipe.setDifficulty(Difficulty.EASY);
        recipe.setServings(8);
        recipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");

        this.recipeRepository.save(recipe);
    }
}
