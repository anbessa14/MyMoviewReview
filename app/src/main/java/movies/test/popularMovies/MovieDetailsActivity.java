package movies.test.popularMovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;

import movies.test.popularMovies.MovieTools.MoviePojo;
import movies.test.popularMovies.Utilities.Constants;


public class MovieDetailsActivity extends AppCompatActivity {
        TextView movieName;
        TextView     rating;
        TextView  synopsis;
        TextView  releaseDate;
        ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        Intent intent = this.getIntent();
       MoviePojo moviePojo =(MoviePojo) intent.getExtras().getSerializable(Constants.INTENT_MOVIE_IDENTIFIER);
        movieName = (TextView) findViewById(R.id.movie_original_title_text_view);
        rating = (TextView) findViewById(R.id.rating_text_view);
        synopsis = (TextView) findViewById(R.id.overview_text_view);
        releaseDate = (TextView) findViewById(R.id.release_date_text_view);
        imageView = (ImageView) findViewById(R.id.movieView);


        movieName.setText(moviePojo.originalTitle);
        rating.setText(String.valueOf(moviePojo.voteAvg));
        synopsis.setText(moviePojo.overview);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        releaseDate.setText(String.valueOf(dateFormat.format(moviePojo.releaseDate)));
        Picasso.with(getApplicationContext()).load(moviePojo.posterPath).into(imageView);
    }
}
