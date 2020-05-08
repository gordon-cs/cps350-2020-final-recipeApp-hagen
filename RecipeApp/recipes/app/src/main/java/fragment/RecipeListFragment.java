package fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.recipes.R;

import java.util.List;

import adapter.RecipeRecyclerViewAdapter;

import networking.response.RecipeList;
import viewmodel.RecipeListFragmentViewModel;



public class RecipeListFragment extends Fragment {

    // Input that user enters
    private final String food_filter = null;

    private RecyclerView rvRecipe;
    private EditText svRecipe;
    private Button btnSearch;
    private TextView dataSource;
    private ImageView ivPlaceHolder;
    private String gifURL = "https://cdn.dribbble.com/users/989157/screenshots/4822481/food-icons-loading-animation.gif";

    // Abstraction of the view
    private RecipeListFragmentViewModel mViewModel;
    private final RecipeRecyclerViewAdapter mAdapter = new RecipeRecyclerViewAdapter();
    public RecipeListFragment() { }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Will either create or reuse an existing view model
        mViewModel = ViewModelProviders.of(this).get(RecipeListFragmentViewModel.class);
        mViewModel.getRecipes(food_filter);

        // This method updates recipe
        listenToLiveDataUpdates();
    }

    private void listenToLiveDataUpdates() {
        // Listening to liveData for recipe updates
        mViewModel.recipeListLiveData.observe(this, new Observer<RecipeList>() {
            @Override
            public void onChanged(RecipeList recipeList) {
                // Recipe search results is nothing, so clear list
                if (recipeList == null || recipeList.hits == null) {
                    // mAdapter.clearList();
                    return;
                }
                // Update adapter with recipe list from retrofit
                List<RecipeList.RecipeItem> allRecipeList = recipeList.hits;
                mAdapter.setRecipeList(allRecipeList);
            }
        });
    }


    // Called to have the fragment instantiate its user interface view.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_recipe_list, container, false);
        // Binds the XML elements with java object using findViewById function
        rvRecipe = inflate.findViewById(R.id.rv_recipe);
        svRecipe = inflate.findViewById(R.id.sv_recipe);
        btnSearch  = inflate.findViewById(R.id.btn_search);
        dataSource = inflate.findViewById(R.id.tv_datasource);
        ivPlaceHolder = inflate.findViewById(R.id.iv_placeholder);

        // Binds the string gifURL to XML element
        Glide.with(this).load(gifURL).into(ivPlaceHolder);

        rvRecipe.setAdapter(mAdapter);
        bindSearchView();
        return inflate;
    }

    // Allows user to search for food type
    private void bindSearchView() {

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataSource.setVisibility(View.GONE);
                ivPlaceHolder.setVisibility(View.GONE);
                String foodTypeSearch = svRecipe.getText().toString();
                mViewModel.getRecipes(foodTypeSearch);
            }
        });
    }

    public static RecipeListFragment newInstance() {
        RecipeListFragment fragment = new RecipeListFragment();
        return fragment;
    }
}