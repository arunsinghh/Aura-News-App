package com.deva.minoor2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.Article;
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView;
    List<Article>articleList=new ArrayList<>();
    newsAdapter adapter;
    LinearProgressIndicator progressIndicator;

    Button gen,eneter,sports,tech,buisness,health,science;

    SearchView searchView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.rcv);
        progressIndicator=findViewById(R.id.probar);

        gen=findViewById(R.id.btn_gen);
        buisness=findViewById(R.id.btn_business);
        eneter=findViewById(R.id.btn_ent);
        sports=findViewById(R.id.btn_sports);
        health=findViewById(R.id.btn_health);
        tech=findViewById(R.id.btn_tech);
        science=findViewById(R.id.btn_science);
        searchView=findViewById(R.id.search_btn);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getNews("GENERAL",query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        setupRecyclerView();
        getNews("GENERAL",null);
        gen.setOnClickListener(this);
        sports.setOnClickListener(this);
        eneter.setOnClickListener(this);
        buisness.setOnClickListener(this);
        tech.setOnClickListener(this);
        health.setOnClickListener(this);
        science.setOnClickListener(this);

    }

    void setupRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new newsAdapter(articleList);

        recyclerView.setAdapter(adapter);
    }
    void chageBar(boolean show){
        if(show){
            progressIndicator.setVisibility(View.VISIBLE);
        }
        else{
            progressIndicator.setVisibility(View.INVISIBLE);
        }
    }

    void getNews(String category,String querry){
        chageBar(true);
        NewsApiClient newsApiClient = new NewsApiClient("9712ef1fab2a46e4aca50b0344a750cc");
        newsApiClient.getTopHeadlines(
                new TopHeadlinesRequest.Builder()
                        .category(category)
                        .language("en")
                        .country("in")
                        .q(querry)
                        .build(),
                new NewsApiClient.ArticlesResponseCallback(){
                    @Override
                    public void onSuccess(ArticleResponse response) {
                             runOnUiThread(()->{
                                 chageBar(false);
                                 articleList=response.getArticles();
                                 adapter.updateData(articleList);
                                 adapter.notifyDataSetChanged();
                        });
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                           System.out.println("failed...............");
                    }


                }
        );
    }

    @Override
    public void onClick(View v) {
        Button btn=(Button) v;
        String category=btn.getText().toString();
        getNews(category,null);
    }
}