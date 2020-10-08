package com.example.a100film.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.a100film.R;
import com.example.a100film.filmRoom.Film;


public class FilmAdapter extends ListAdapter<Film, FilmAdapter.FilmHolder> {
    private OnItemClickListener listener;
    private Context mContext;

    public FilmAdapter(Context mContext) {
        super(DIFF_CALLBACK);
        this.mContext=mContext;
    }

    private static final DiffUtil.ItemCallback<Film> DIFF_CALLBACK=new DiffUtil.ItemCallback<Film>() {
        @Override
        public boolean areItemsTheSame(@NonNull Film oldItem, @NonNull Film newItem) {
            return oldItem.getListNumbers() == newItem.getListNumbers();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Film oldItem, @NonNull Film newItem) {
            return oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getNameEng().equals(newItem.getNameEng()) &&
                    oldItem.getRating().equals(newItem.getRating());
        }
    };

    @NonNull
    @Override
    public FilmHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_film, parent, false);
        return new FilmHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmHolder holder, int position) {
        Film currentFilm = getItem(position);
        holder.name.setText(currentFilm.getName());
        holder.nameEng.setText(currentFilm.getNameEng());
        holder.rank.setText("IMDB\n"+currentFilm.getRating());
        holder.listNumbers.setText(currentFilm.getListNumbers());
        Glide.with(mContext).load(currentFilm.getNimage()).into(holder.img);
    }





    public Film getFilmAt(int position) {
        return getItem(position);
    }


    class FilmHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView nameEng;
        private TextView rank;
        private TextView listNumbers;
        private  ImageView img;

        public FilmHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            nameEng = itemView.findViewById(R.id.nameEng);
            rank=itemView.findViewById(R.id.rank);
            listNumbers = itemView.findViewById(R.id.listNumbers);
            img= itemView.findViewById(R.id.imageView2);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position!=RecyclerView.NO_POSITION){
                        listener.onItemClick(getItem(position));
                    }

                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Film film);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}

