package org.belosoft.cardview.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import org.belosoft.cardview.adpaters.MyAdapter;
import org.belosoft.cardview.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private List<org.belosoft.cardview.models.Movie> movies;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movies = this.getAllMovies();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this); // formato linearLayout

        mAdapter = new MyAdapter(movies, R.layout.recycler_view_item, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(org.belosoft.cardview.models.Movie movie, int position) {
                //Toast.makeText(MainActivity.this, name + " - " + position, Toast.LENGTH_LONG).show();
                removeMovie(position);
            }
        });

        // para tamaño fijo del recycler si sabemos que así será, mejora performance
        mRecyclerView.setHasFixedSize(true);
        // animacion
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_name:
                this.addMovie(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private List<org.belosoft.cardview.models.Movie> getAllMovies(){
        return new ArrayList<org.belosoft.cardview.models.Movie>(){{
            add(new org.belosoft.cardview.models.Movie("Apple", R.drawable.apple_bg));
            add(new org.belosoft.cardview.models.Movie("Banana", R.drawable.banana_bg));
            add(new org.belosoft.cardview.models.Movie("Cherry", R.drawable.cherry_bg));
            add(new org.belosoft.cardview.models.Movie("Orange", R.drawable.orange_bg));
        }};
    }

    private void addMovie(int position){
        movies.add(position, new org.belosoft.cardview.models.Movie("New image " + (++counter), R.drawable.playa));
        mAdapter.notifyItemInserted(position);
        mLayoutManager.scrollToPosition(position);
    }

    private void removeMovie(int position){
        movies.remove(position);
        mAdapter.notifyItemRemoved(position);
    }
}
