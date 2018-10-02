package com.khushi.win10.cottagebooking;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

public class HorizontalImageListAdapter extends RecyclerView.Adapter<HorizontalImageListAdapter.ViewHolder> {
    private String[] pathList;
    private Callback callback;

    HorizontalImageListAdapter(String[] pathList,Callback callback){
        this.pathList = pathList;
        this.callback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_horizontal_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.bind(pathList[position]);
        holder.smallImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.selectedItem(pathList[position]);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pathList.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView smallImage;
        private LinearLayout imageContainer;
        ViewHolder(View itemView){
            super(itemView);
            smallImage = itemView.findViewById(R.id.horizontal_image_image);
            imageContainer = itemView.findViewById(R.id.horizontal_image_main_container);

        }

        void bind(String path){
            Picasso.get().load(path).into(smallImage);
        }


    }

    interface Callback{
        void selectedItem(String path);
    }
}
