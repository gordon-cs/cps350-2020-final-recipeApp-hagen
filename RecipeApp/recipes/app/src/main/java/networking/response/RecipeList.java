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
        public final List<String> ingredientLines;
        public final List<String> healthLabels;
        public final double calories;


        public Recipe(String uri, String label, String image, String source, String url, String shareAs, List<String> ingredientLines, List<String> healthLabels, double calories) {
            this.uri = uri;
            this.label = label;
            this.image = image;
            this.source = source;
            this.url = url;
            this.shareAs = shareAs;
            this.ingredientLines = ingredientLines;
            this.healthLabels = healthLabels;
            this.calories = calories;
        }
    }

}