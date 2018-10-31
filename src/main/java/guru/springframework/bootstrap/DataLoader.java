package guru.springframework.bootstrap;

import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Ingredient;
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
        Ingredient avocado = new Ingredient();
        avocado.setDescription("ripe avocados");
        avocado.setAmount(new BigDecimal(2));

        Ingredient salt = new Ingredient();
        salt.setDescription("salt");
        salt.setAmount(new BigDecimal(.5));
        salt.setUnitOfMeasure(this.unitOfMeasureRepository.findByDescription("Teaspoon").get());

        Ingredient lime = new Ingredient();
        lime.setDescription("fresh lime juice or lemon juice");
        lime.setAmount(new BigDecimal(.5));
        lime.setUnitOfMeasure(this.unitOfMeasureRepository.findByDescription("Tablespoon").get());

        Recipe recipe = new Recipe();

        recipe.setDescription("The BEST guacamole! So easy to make with ripe avocados, salt, serrano chiles, " +
                "cilantro and lime. Garnish with red radishes or jicama. Serve with tortilla chips. Watch how to " +
                "make guacamole - it's easy!");

        recipe.setCookTime(0);
        recipe.setPrepTime(10);
        recipe.getIngredients().add(avocado);
        recipe.getIngredients().add(salt);
        recipe.getIngredients().add(lime);
        recipe.setDifficulty(Difficulty.EASY);
        recipe.setServings(8);
        recipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");

        avocado.setRecipe(recipe);
        salt.setRecipe(recipe);
        lime.setRecipe(recipe);

        this.recipeRepository.save(recipe);
    }
}
