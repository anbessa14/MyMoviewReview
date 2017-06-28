package movies.test.popularMovies;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;

import movies.test.popularMovies.MovieTools.MoviePojo;
import movies.test.popularMovies.Utilities.Constants;
import movies.test.popularMovies.MovieTools.JsonMovieUtility;
import movies.test.popularMovies.Utilities.NetworkUtility;

public class MainActivity extends AppCompatActivity  implements MovieDetailsClick{

    private String saveMovieStateKey = "movie_state_key";
    private RecyclerView movieRecyclerView;
    private MovieAdapter movieRecyclerViewAdapter;
    private ProgressBar progressBar;
    private TextView errorMessageView;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        errorMessageView = (TextView) findViewById(R.id.error_field);
        movieRecyclerView = (RecyclerView) findViewById(R.id.movie_recycler_view);
        movieRecyclerViewAdapter = new MovieAdapter(Picasso.with(getApplicationContext()), this);
        setupRecyclerView();
        if(savedInstanceState != null)
        {

            displayMovies((ArrayList<MoviePojo>) savedInstanceState.getSerializable(saveMovieStateKey));
        }
        else
         {
        retrieveTopMovies();
        }


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(movieRecyclerViewAdapter.getMoviesList() != null && movieRecyclerViewAdapter.getMoviesList().size() > 0 )
        {
            outState.putSerializable(saveMovieStateKey,movieRecyclerViewAdapter.getMoviesList());
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int  menuButtonIdentifier = item.getItemId();

        if(menuButtonIdentifier == R.id.action_popular)
        {
            retrievePopularMovies();
        } else if(menuButtonIdentifier == R.id.action_top_rated)
        {
            retrieveTopMovies();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.movie_list_type,menu);
        return true;

    }


    private void retrievePopularMovies()
    {
        new MovieFetcherTask().execute(Constants.MOST_POPULAR_IDENTIFIER);

    } private void retrieveTopMovies()
    {
        new MovieFetcherTask().execute(Constants.TOP_RATED_IDENTIFIER);
    }

    private void setupRecyclerView()
    {
        movieRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        movieRecyclerView.setAdapter(movieRecyclerViewAdapter);
        movieRecyclerView.setHasFixedSize(true);

    }

    @Override
    public void onMoviePosterClick(MoviePojo moviePojo) {
        Intent intent = new Intent( this ,MovieDetailsActivity.class);
        intent.putExtra(Constants.intentMovieIdentifier,moviePojo);
        startActivity(intent);

    }


    public class MovieFetcherTask extends AsyncTask<String, Void, ArrayList<MoviePojo>>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            errorMessageView.setVisibility(View.GONE);
            movieRecyclerView.setVisibility(View.GONE);
        }

        @Override
        protected ArrayList<MoviePojo> doInBackground(String... params) {
            if (params.length == 0) {
                return null;
            }
            URL movieServiceURL = null;
            if(params[0].equalsIgnoreCase(Constants.MOST_POPULAR_IDENTIFIER))
            {
                 movieServiceURL = NetworkUtility.getPopularMovieURL();
            }else if (params[0].equalsIgnoreCase(Constants.TOP_RATED_IDENTIFIER))
            {
                 movieServiceURL = NetworkUtility.getTopRatedMovieURL();
            }

            try {
                String jsonMovieResponse = NetworkUtility
                        .getHttpResponseUrl(movieServiceURL);

               return JsonMovieUtility.generateParsedMovieDataFromJson(jsonMovieResponse);


            } catch (Exception e) {
                e.printStackTrace();
                displayError();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<MoviePojo> moviePojoArrayList) {
            super.onPostExecute(moviePojoArrayList);
            displayMovies(moviePojoArrayList);

        }
    }



    private void displayMovies(ArrayList<MoviePojo> moviePojoArrayList)
    {
        movieRecyclerViewAdapter.setMoviesList(moviePojoArrayList);
        progressBar.setVisibility(View.GONE);
        errorMessageView.setVisibility(View.GONE);
        movieRecyclerView.setVisibility(View.VISIBLE);
    }

    private void displayError()
    {
        progressBar.setVisibility(View.GONE);
        errorMessageView.setVisibility(View.VISIBLE);
        movieRecyclerView.setVisibility(View.GONE);
    }

}

