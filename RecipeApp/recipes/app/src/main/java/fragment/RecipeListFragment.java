package fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.recipes.R;
import com.example.recipes.databinding.FragmentRecipeListBinding;

import java.util.List;

import adapter.RecipeRecyclerViewAdapter;

import networking.response.RecipeList;
import viewmodel.RecipeListFragmentViewModel;



public class RecipeListFragment extends Fragment {

    // Declares data binding, view model, recycler view adapter
    private FragmentRecipeListBinding mDataBind;
    private RecipeListFragmentViewModel mViewModel;
    private final RecipeRecyclerViewAdapter mAdapter = new RecipeRecyclerViewAdapter();
    public RecipeListFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(RecipeListFragmentViewModel.class);
        mViewModel.getRecipes();


        listenToLiveDataUpdates();
    }

    private void listenToLiveDataUpdates() {
        // Listening to liveData for recipe updates.
        mViewModel.recipeListLiveData.observe(this,   new Observer<RecipeList>() {
            @Override
            public void onChanged(RecipeList recipeList) {
                // Update adapter with recipe list from retrofit
                List<RecipeList.RecipeItem> allRecipeList = recipeList.hits;
                mAdapter.setRecipeList(allRecipeList);
            }
        });
    }


    // Called to have the fragment instantiate its user interface view.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Data binding
        mDataBind = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_recipe_list, container, false);
        mDataBind.rvRecipe.setAdapter(mAdapter);

        return mDataBind.getRoot();
    }

    public static RecipeListFragment newInstance() {
        RecipeListFragment fragment = new RecipeListFragment();
        return fragment;
    }
}