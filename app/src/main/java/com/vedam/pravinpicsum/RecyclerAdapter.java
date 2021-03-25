package com.vedam.pravinpicsum;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.vedam.pravinpicsum.API.Models.Pics;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    List<Pics> picsList;
   // private final ItemClicked listener;
    Context context;
    private static final String URL = "https://picsum.photos/300/300?image=";

    public RecyclerAdapter(Context context, List<Pics> picsList) {

        this.picsList =picsList;
        this.context = context;

    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_picsum_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {

        Pics pics = picsList.get(position);
        holder.progressBar.setVisibility(View.VISIBLE);
        Glide.with(context)
                .load(URL+pics.id)
                .centerCrop()
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(holder.imageView);

        holder.textView.setText(pics.author);


    }

    @Override
    public int getItemCount() {
        return picsList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;
        ProgressBar progressBar;


        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.ivPost);
            textView = itemView.findViewById(R.id.tvAuthor);
            progressBar = itemView.findViewById(R.id.progress_circular);

        }
    }

//    interface ItemClicked {
//        void onItemClicked(Pics item);
//    }
}
