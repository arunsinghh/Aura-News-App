package com.deva.minoor2;


        import android.content.Intent;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;
        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import com.kwabenaberko.newsapilib.models.Article;
        import com.squareup.picasso.Picasso;

        import java.util.List;
        import java.util.concurrent.atomic.AtomicBoolean;

public class newsAdapter extends RecyclerView.Adapter<newsAdapter.NewsViewHolder> {

    private List<Article> newsList;

    public newsAdapter(List<Article> newsList) {
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_recycler_item, parent, false);
        return new NewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        Article article = newsList.get(position);
        holder.titleTextView.setText(article.getTitle());
        holder.sourceTextView.setText(article.getContent());
        Picasso.get().load(article.getUrlToImage())
                .error(R.drawable.noimg)
                .into(holder.imageView);




        holder.itemView.setOnClickListener((v ->{
            Intent intent=new Intent(v.getContext(),News_Full.class);
            intent.putExtra("url",article.getUrl());
            v.getContext().startActivity(intent);
        }));
    }

    void updateData(List<Article> data){
        newsList.clear();
        newsList.addAll(data);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
         TextView titleTextView, sourceTextView;
         ImageView imageView;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.tvTitle);
            sourceTextView = itemView.findViewById(R.id.tvSource);
            imageView=itemView.findViewById(R.id.ivArticleImage);

        }
    }
}
