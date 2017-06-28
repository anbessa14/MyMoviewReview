package movies.test.popularMovies.MovieTools;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import movies.test.popularMovies.Utilities.Constants;

/**
 * Created by ZCP on 6/26/17.
 */

public  final class JsonMovieUtility {



    private static String [] movieJsonResult;
    private static final String DATA_CONTAINER = "results";
    private static final String MOVIE_POSTER="poster_path";




    public  static ArrayList<MoviePojo> generateParsedMovieDataFromJson(String movieJsonStr)
            throws JSONException {

        JSONObject movieJson =null;
        JSONArray movieArray = null;
        ArrayList<MoviePojo> movieList = null;
        if(movieJsonStr != null)
        {
             movieJson = new JSONObject(movieJsonStr);
        }
        if(movieJson != null)
        {
            movieArray = movieJson.getJSONArray(DATA_CONTAINER);
            MoviePojo movieElement = null;
            movieList = new ArrayList<MoviePojo>(movieArray.length());
            for(int i=0; i< movieArray.length();i++)
            {
                movieElement = getMovieFromJSON(movieArray.getJSONObject(i));
                movieList.add(movieElement);
            }
        }
        return  movieList;



    }


    public static MoviePojo getMovieFromJSON(JSONObject movieJSON){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            MoviePojo movie = new MoviePojo();

            movie.votes = movieJSON.getInt("vote_count");
            movie.id = movieJSON.getInt("id");
            movie.voteAvg = movieJSON.getDouble("vote_average");
            movie.title = movieJSON.getString("title");
            movie.originalTitle = movieJSON.getString("original_title");
            movie.posterPath = Constants.MOVIE_POSTER_BASE_URL + movieJSON.getString("poster_path");
            movie.backdropPath = Constants.MOVIE_IMAGE_BASE_URL_PATH + movieJSON.getString("backdrop_path");
            movie.overview = movieJSON.getString("overview");
            movie.releaseDate = dateFormat.parse(movieJSON.getString("release_date"));

            return movie;
        }
        catch (JSONException | ParseException e){
            Log.d(JsonMovieUtility.class.getName(),e.toString());
        }

        return null;
    }



}