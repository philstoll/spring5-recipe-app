package guru.springframework.bootstrap;

import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Notes;
import guru.springframework.domain.Recipe;
import guru.springframework.repository.CategoryRepository;
import guru.springframework.repository.IngredientRepository;
import guru.springframework.repository.RecipeRepository;
import guru.springframework.repository.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@Profile("default")
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;
    private RecipeRepository recipeRepository;
    private IngredientRepository ingredientRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository,
                           RecipeRepository recipeRepository, IngredientRepository ingredientRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("Loading Bootstrap Data...");
        this.recipeRepository.saveAll(this.getRecipes());
    }

    private List<Recipe> getRecipes() {

        Ingredient avocado = new Ingredient("ripe avocados", new BigDecimal(2), this.unitOfMeasureRepository.findByDescription("").get());
        Ingredient salt = new Ingredient("salt", new BigDecimal(.5), this.unitOfMeasureRepository.findByDescription("Teaspoon").get());
        Ingredient lime = new Ingredient("fresh lime juice or lemon juice", new BigDecimal(.5), this.unitOfMeasureRepository.findByDescription("Tablespoon").get());

        Notes notes = new Notes("The BEST guacamole! So easy to make with ripe avocados, salt, serrano " +
                " chiles,cilantro and lime. Garnish with red radishes or jicama. Serve with tortilla chips. " +
                "Watch how to make guacamole - it's easy!");

        Recipe recipe = new Recipe();

        recipe.setNotes(notes);
        recipe.setDescription("Perfect Guacamole!");
        recipe.setCookTime(0);
        recipe.setPrepTime(10);
        recipe.addIngredient(avocado)
                .addIngredient(salt)
                .addIngredient(lime);
        recipe.setDifficulty(Difficulty.EASY);
        recipe.setServings(8);
        recipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");

        List<Recipe> recipes = new ArrayList<>();
        recipes.add(recipe);

        return recipes;
    }
}
