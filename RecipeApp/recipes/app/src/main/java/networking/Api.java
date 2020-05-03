package networking;

import networking.response.RecipeList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

// https://square.github.io/retrofit/
public interface Api {
    @GET("search?q=&app_id=cdf796be&app_key=8a06499d4cd1a77724a5f2a37c0ae237&from=0&to=3&calories=591-722&health=alcohol-free")
    Call<RecipeList> getRecipes();

    // Fetches this call to allow user to search
    @GET("search?app_id=cdf796be&app_key=8a06499d4cd1a77724a5f2a37c0ae237&from=0&to=3&calories=591-722&health=alcohol-free")
    Call<RecipeList> getRecipes(@Query("q") String foodType);
}

