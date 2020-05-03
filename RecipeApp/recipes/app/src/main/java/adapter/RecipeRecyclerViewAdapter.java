package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.recipes.R;
import com.example.recipes.databinding.ItemRecipeBinding;

import java.util.ArrayList;
import java.util.List;

import fragment.RecipeListFragment;
import networking.response.RecipeList;


public class RecipeRecyclerViewAdapter<MyViewHolder> extends RecyclerView.Adapter<RecipeRecyclerViewAdapter.MyViewHolder> {

    // Abstract recipe list
    List<RecipeList.RecipeItem> recipeList = new ArrayList<>();

    // Creates a constructor based on data set
    public void setRecipeList(List<RecipeList.RecipeItem> recipeList) {
        this.recipeList = recipeList;
        notifyDataSetChanged();
    }

//    public void setIngredientList(List<IngredientList.ingredients> ingredientList) {
//        this.RecipeList.RecipeItem.IngredientItem.ingredient = ingredientList;
//        notifyDataSetChanged();
//    }

    // Creates new views when RecipeRecyclerView needs a new RecyclerView.ViewHolder of the given type to represent an item.
    @NonNull
    @Override
    public RecipeRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Creates a new view
        Context context = parent.getContext();
        ItemRecipeBinding dataBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_recipe, parent, false);

        // Set the view's size and layout parameters
        return new RecipeRecyclerViewAdapter.MyViewHolder(dataBinding);
    }

    // Displays the data at a specified position.
    @Override
    public void onBindViewHolder(@NonNull RecipeRecyclerViewAdapter.MyViewHolder holder, int position) {

        // Updates the content holder by replacing it with elements from your data set at a given position
        RecipeList.RecipeItem recipeItem = recipeList.get(position);
        RecipeList.Recipe recipe = recipeItem.recipe;
        holder.binding.tvTitle.setText(recipe.label);

//        RecipeList.RecipeItem.IngredientItem ingredientItem = ingredientList.get(position);
//        RecipeList.Ingredient ingredient = RecipeList.RecipeItem.IngredientItem.ingredient;
//        holder.binding.tvIngredientLines.setText(ingredient.text);

        // https://github.com/bumptech/glide
        // Displays the image
        Glide.with(holder.itemView).load(recipe.image).into(holder.binding.ivRecipe);

        holder.binding.tvSource.setText(recipe.source);
        holder.binding.tvUrl.setText(recipe.url);
       //holder.binding.tvIngredientLines.setText(recipe.ingredientLines);
   }

    //Returns the total number of items in the data set held by the adapter.
    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final ItemRecipeBinding binding;

        public MyViewHolder(ItemRecipeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}