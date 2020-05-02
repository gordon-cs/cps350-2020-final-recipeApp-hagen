package activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.recipes.R;

import fragment.RecipeListFragment;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, RecipeListFragment.newInstance())
                .commit();
    }
}
