package adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.recipes.R;

import java.util.ArrayList;
import java.util.List;

import networking.response.RecipeList;


public class RecipeRecyclerViewAdapter<MyViewHolder> extends RecyclerView.Adapter<RecipeRecyclerViewAdapter.MyViewHolder> {

    // Abstract recipe list
    private List<RecipeList.RecipeItem> recipeList = new ArrayList<>();

    public void clearList() {
        this.recipeList.clear();
        notifyDataSetChanged();
    }

    // Creates a constructor based on data set
    public void setRecipeList(List<RecipeList.RecipeItem> recipeList) {
        this.recipeList = recipeList;
        notifyDataSetChanged();
    }


    // Creates new views when RecipeRecyclerView needs a new RecyclerView.ViewHolder of the given type to represent an item.
    @NonNull
    @Override
    public RecipeRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Creates a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe, parent, false);
        return new RecipeRecyclerViewAdapter.MyViewHolder(view);
    }

    // Displays the data at a specified position.
    @Override
    public void onBindViewHolder(@NonNull RecipeRecyclerViewAdapter.MyViewHolder holder, int position) {

        // Updates the content holder by replacing it with elements from your data set at a given position
        RecipeList.RecipeItem recipeItem = recipeList.get(position);
        RecipeList.Recipe recipe = recipeItem.recipe;
        holder.tvTitle.setText(recipe.label);


        // https://github.com/bumptech/glide
        // Displays the image
        Glide.with(holder.itemView).load(recipe.image).into(holder.ivRecipe);

        holder.tvSource.setText(recipe.source);
        holder.tvHealthLabel.setText(recipe.healthLabels.toString());
        holder.tvUrl.setText(recipe.url);

        List<String> ingredientList = recipe.ingredientLines;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(holder.rootView.getContext(), R.layout.item_ingredient, R.id.tv_ingredient, ingredientList);
        holder.listIngredients.setAdapter(adapter);
    }

    //Returns the total number of items in the data set held by the adapter.
    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final View rootView;
        private final TextView tvSource;
        private final TextView tvHealthLabel;
        private final TextView tvUrl;
        private final TextView tvTitle;
        private final ListView listIngredients;
        private final ImageView ivRecipe;

        public MyViewHolder(View view) {
            super(view);
            rootView = view;
            tvSource = view.findViewById(R.id.tv_source);
            tvHealthLabel = view.findViewById(R.id.tv_healthLabel);
            tvUrl = view.findViewById(R.id.tv_url);
            tvTitle = view.findViewById(R.id.tv_title);
            listIngredients = view.findViewById(R.id.list_ingredients);
            ivRecipe = view.findViewById(R.id.iv_recipe);
        }
    }
}