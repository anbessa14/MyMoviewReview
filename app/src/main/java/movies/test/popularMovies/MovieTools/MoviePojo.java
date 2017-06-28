package movies.test.popularMovies.MovieTools;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ZCP on 6/26/17.
 */

public class MoviePojo implements Serializable{

    public int    votes;
    public long   id;
    public Date   releaseDate;
    public double voteAvg;
    public double popularity;
    public String title;
    public String originalTitle;
    public String posterPath;
    public String backdropPath;
    public String overview;

}
