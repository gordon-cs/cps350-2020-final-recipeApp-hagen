package networking.response;

import java.util.List;

public class RecipeList {

    public final List<RecipeItem> hits;

    // Recipe list with recipe items inside array hits
    public RecipeList(List<RecipeItem> hits) {
        this.hits = hits;
    }

    // Recipe objects with recipes items inside
    public static class RecipeItem {
        public final Recipe recipe;

        public RecipeItem(Recipe recipe) {
            this.recipe = recipe;
        }
    }



    public static class Recipe {
        public final String uri;
        public final String label;
        public final String image;
        public final String source;
        public final String url;
        public final String shareAs;
        public final List<Ingredients> ingredients;
        public final List<String> healthLabels;
        public final double calories;

        public Recipe(String uri, String label, String image, String source, String url, String shareAs, List<Ingredients> ingredients, List<String> healthLabels, double calories) {
            this.uri = uri;
            this.label = label;
            this.image = image;
            this.source = source;
            this.url = url;
            this.shareAs = shareAs;
            this.ingredients = ingredients;
            this.healthLabels = healthLabels;
            this.calories = calories;
        }
    }

    // Ingredients list with objects inside  it
    public static class Ingredients {
        public final String text;
        public final double weight;

        public Ingredients(String text, double weight) {
            this.text = text;
            this.weight = weight;
        }
    }
}

/*
ingredients" : [ {
        "text" : "3 cups cooked short-grain white rice, or 2 cups white rice and 1 cup brown rice",
        "weight" : 585.0
        }, {
        "text" : "3 cups cooked short-grain white rice, or 2 cups white rice and 1 cup brown rice",
        "weight" : 190.0
        }, {
        "text" : "1/4 cup soy sauce for glazing",
        "weight" : 63.75
        } ],
 */