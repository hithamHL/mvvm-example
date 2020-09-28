package com.hithamhl.faceboov.data;

import com.hithamhl.faceboov.pojo.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostInterface {

    @GET("todos")
    Call<List<PostModel>> getAllPost();
}
