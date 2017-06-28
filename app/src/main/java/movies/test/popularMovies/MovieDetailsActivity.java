package movies.test.popularMovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewDebug;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import movies.test.popularMovies.MovieTools.MoviePojo;
import movies.test.popularMovies.Utilities.Constants;


public class MovieDetailsActivity extends AppCompatActivity {
    TextView movieName, rating , synopsis, releaseDate;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        Intent intent = this.getIntent();
       MoviePojo moviePojo =(MoviePojo) intent.getExtras().getSerializable(Constants.intentMovieIdentifier);
        movieName = (TextView) findViewById(R.id.movie_original_title_text_view);
        rating = (TextView) findViewById(R.id.rating_text_view);
        synopsis = (TextView) findViewById(R.id.overview_text_view);
        releaseDate = (TextView) findViewById(R.id.release_date_text_view);
        imageView = (ImageView) findViewById(R.id.movieView);


        movieName.setText(moviePojo.originalTitle);
        rating.setText(String.valueOf(moviePojo.voteAvg));
        synopsis.setText(moviePojo.overview);
        releaseDate.setText(String.valueOf(moviePojo.releaseDate));
        Picasso.with(getApplicationContext()).load(moviePojo.posterPath).into(imageView);
    }
}
