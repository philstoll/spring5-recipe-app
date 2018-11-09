package guru.springframework.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryTest {

    private Category category;

    @Before
    public void setUp() {
        category = new Category();
    }

    @Test
    public void getId() {
        Long idValue = 4L;
        this.category.setId(idValue);
        assertEquals(idValue, this.category.getId());
    }

    @Test
    public void getDescription() {
        String description = "Recipe description";
        this.category.setDescription(description);
        assertEquals(description, this.category.getDescription());
    }

    @Test
    public void getRecipes() {
    }
}