package networking.response;

import java.util.List;

public class RecipeList {

    //
    public List<RecipeItem> hits;

    public RecipeList(List<RecipeItem> hits) {
        this.hits = hits;
    }

    public class RecipeItem {
        public Recipe recipe;

        public RecipeItem(Recipe recipe) {
            this.recipe = recipe;
        }

//        public IngredientList(List<IngredientItem> lines) {
//            this.lines = lines;
//        }

        public class IngredientItem {
            public Ingredient ingredient;

            public IngredientItem(Ingredient ingredient) {
                this.ingredient = ingredient;
            }
        }

    }


    public class Recipe {
        public String uri;
        public String label;
        public String image;
        public String source;
        public String url;
        public String shareAs;

        public Recipe(String uri, String label, String image, String source, String url, String shareAs) {
            this.uri = uri;
            this.label = label;
            this.image = image;
            this.source = source;
            this.url = url;
            this.shareAs = shareAs;
        }
    }

    public class Ingredient {
        public String text;

        public Ingredient(String text) {
            this.text = text;
        }
    }
}


