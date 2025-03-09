package com.ita.mybestyoutube.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ita.mybestyoutube.R;
import com.ita.mybestyoutube.pojos.Video;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private List<Video> videos;

    public class VideoViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitre;
        public TextView tvDescription;
        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitre = itemView.findViewById(R.id.tvTitre);
            tvDescription = itemView.findViewById(R.id.tvDescription);
        }
    }

    public VideoAdapter(List<Video> videos) {
        this.videos = videos;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.video_item, parent, false);

        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        Video video = videos.get(position);
        holder.tvTitre.setText(video.getTitre());
        holder.tvDescription.setText(video.getDescription());
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }
}
