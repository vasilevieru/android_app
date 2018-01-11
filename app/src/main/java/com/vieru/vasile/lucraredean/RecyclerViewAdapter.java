package com.vieru.vasile.lucraredean;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * Created by Vasile on 04.05.2017.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private List<Kebab> kebabList;

    public RecyclerViewAdapter(List<Kebab> kebabList) {
        super();
        this.kebabList = kebabList;
    }

    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.file, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.MyViewHolder holder, int position) {
        Kebab kebab = kebabList.get(position);
        holder.textViewName.setText(kebab.getTitle());
        holder.textViewAddresse.setText(kebab.getAddrese());
        holder.imageView.setImageBitmap(kebab.getImage());
        holder.ratingBar.setRating(kebab.getRating());

    }

    @Override
    public int getItemCount() {
        return kebabList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView textViewName, textViewAddresse;
        public RatingBar ratingBar;

        public MyViewHolder(View itemView) {
            super(itemView);
            //imageView = (NetworkImageView) itemView.findViewById(R.id.imageView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            textViewName = (TextView) itemView.findViewById(R.id.textViewDenumire);
            textViewAddresse = (TextView) itemView.findViewById(R.id.textViewAddress);
            ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);
        }
    }
}
