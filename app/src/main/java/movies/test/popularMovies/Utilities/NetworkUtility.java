package movies.test.popularMovies.Utilities;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by ZCP on 6/26/17.
 */

public class NetworkUtility
{

   private final static String API_KEY = "68a7db991b43f986439d6554c32556ec";
    final static String PARAM_LANGUAGE="language=en-US";
    final static String PARAM_PAGE="page=2";


    public static URL getPopularMovieURL() {
        Uri builtUri = Uri.parse(Constants.MOVIE_BASE_URL).buildUpon()
                .path(Constants.POPULAR_MOVIE_URL_PATH)
                .appendQueryParameter(Constants.PARAM_API,API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }
    public static URL getTopRatedMovieURL() {
        Uri builtUri = Uri.parse(Constants.MOVIE_BASE_URL).buildUpon()
                .path( Constants.TOP_RATED_MOVIE_URL_PATH)
                .appendQueryParameter(Constants.PARAM_API,API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }


    public static String getHttpResponseUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }



}





