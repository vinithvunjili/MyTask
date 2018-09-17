package com.example.volive.mytask;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by volive on 1/9/2018.
 */

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.MyViewHolder> {
     Context context;
     ArrayList<String> namesArr=new ArrayList<>();
    ArrayList<String> imageArr=new ArrayList<>();
    String imgpath;
    public ServiceAdapter(Context ctx,ArrayList<String> name,ArrayList<String> image,String path){
        context=ctx;
        namesArr=name;
        imageArr=image;
        imgpath=path;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.service_name_text);
            imageView = (ImageView) view.findViewById(R.id.service_img);

        }
    }


//    public ServiceAdapter(List<Movie> moviesList) {
//        this.moviesList = moviesList;
//    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.service_list_row, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.title.setText(namesArr.get(position));
        Picasso.with(context).load(imgpath+"/"+imageArr.get(position)).into(holder.imageView);
       /* Glide
                .with(context)
                .load(imageArr.get(position))

                .into(holder.imageView);*/
    }

    @Override
    public int getItemCount() {
        return namesArr.size();
    }
}