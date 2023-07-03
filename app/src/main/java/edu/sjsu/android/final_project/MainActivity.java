package edu.sjsu.android.final_project;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;


public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener{
    public static Context contextOfApplication;
    public static Context getContextOfApplication()
    {
        return contextOfApplication;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavigationBarView bottomNavigationView = (NavigationBarView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        contextOfApplication = getApplicationContext();
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                navigation_helper(R.id.action_global_add2);
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.personal_home, menu);
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();
        if(id == R.id.personal_home) {
            navigation_helper(R.id.action_global_personal_home2);
        }
        return super.onOptionsItemSelected(item);
    }

   public boolean navigation_helper(int id){

           NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
           assert navHostFragment != null;
           NavController controller = navHostFragment.getNavController();
           controller.navigate(id);

           System.out.println("Home Button Clicked");
           return true;
   }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.home) {
            return navigation_helper(R.id.action_global_home2);
        }
        if(id == R.id.quotes) {
            return navigation_helper(R.id.action_global_quotes2);
        }
        if(id == R.id.chat) {
           return navigation_helper(R.id.action_global_chat2);
        }
        if(id == R.id.stats) {
          return navigation_helper(R.id.action_global_stats2);
        }
        return false;
    }
}