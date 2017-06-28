package movies.test.popularMovies;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import java.util.ArrayList;

import movies.test.popularMovies.MovieTools.MoviePojo;

/**
 * Created by ZCP on 6/26/17.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    private MovieDetailsClick clickListener;
    private ArrayList<MoviePojo> moviesList;
    private final Picasso picccasoObject;

    public ArrayList<MoviePojo> getMoviesList() {
        return moviesList;
    }

    public void setMoviesList(ArrayList<MoviePojo> moviesList) {
        this.moviesList = moviesList;
        notifyDataSetChanged();
    }


    public MovieAdapter(Picasso initializedPicasso, MovieDetailsClick clickListener) {
        this.picccasoObject = initializedPicasso;
        this.clickListener = clickListener;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {
        MoviePojo movieObject = moviesList.get(position);
        holder.setMovieToViewHolder(movieObject);
    }

    @Override
    public int getItemCount() {
        return (moviesList == null) ? 0 : moviesList.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder {

        private ImageView posterView;

        public MovieHolder(View itemView) {
            super(itemView);
            posterView = (ImageView) itemView.findViewById(R.id.posterElement);

        }

        public void setMovieToViewHolder(final MoviePojo movieToSet) {
            picccasoObject.load(movieToSet.posterPath).into(posterView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null) {
                        clickListener.onMoviePosterClick(movieToSet);
                    }
                }
            });

        }
    }
}
    interface MovieDetailsClick
    {
         void onMoviePosterClick(MoviePojo moviePojo);
    }

