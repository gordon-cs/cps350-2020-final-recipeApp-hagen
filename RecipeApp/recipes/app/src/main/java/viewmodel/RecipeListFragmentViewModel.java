package viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import networking.RetrofitApi;
import networking.response.RecipeList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeListFragmentViewModel extends ViewModel {

    public MutableLiveData<RecipeList> recipeListLiveData = new MutableLiveData();

    // This calls the API using Retrofit
    public void getRecipes() {
        RetrofitApi
                .singleton()
                .getRecipes()
                .enqueue(new Callback<RecipeList>() {

                    // https://stackoverflow.com/questions/51780696/how-to-make-retrofit-api-call-using-viewmodel-and-livedata
                    @Override
                    public void onResponse(Call<RecipeList> call, Response<RecipeList> response) {
                        RecipeList recipeList = response.body();

                        // Update the liveData to pass recipeList back to fragment.
                        recipeListLiveData.postValue(recipeList);
                    }

                    @Override
                    public void onFailure(Call<RecipeList> call, Throwable t) {

                    }
                });
    }
}
