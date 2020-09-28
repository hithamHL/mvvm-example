package com.hithamhl.faceboov.data;

import com.hithamhl.faceboov.pojo.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostClient {

    private final String BASE_URL="https://jsonplaceholder.typicode.com/";

    //get instance from interface
   private PostInterface postInterface;

   private static PostClient Instance;

    public static PostClient getInstance() {
        if (Instance==null){
            Instance=new PostClient();
        }
        return Instance;
    }

    public PostClient() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        postInterface=retrofit.create(PostInterface.class);
    }

    public Call<List<PostModel>> getPost(){

        return postInterface.getAllPost();
    }
}
