package com.hithamhl.faceboov.ui.main;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.hithamhl.faceboov.data.PostClient;
import com.hithamhl.faceboov.pojo.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostRepository {
    private static final String TAG = "PostRepository";
    private Application application;

  private   MutableLiveData<List<PostModel>> mutableLiveDataPost=new MutableLiveData<>();

    private MutableLiveData<Boolean> mutableLiveDataProgress=new MutableLiveData<>();




    public MutableLiveData<List<PostModel>> getMutableLiveDataPost(){
        Log.d(TAG, "getMutableLiveDataPost: ");

        mutableLiveDataProgress.setValue(true);

        PostClient.getInstance().getPost().enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                Log.d(TAG, "Response: "+response.message());
                if (response.isSuccessful()&&response.body()!=null) {
                    mutableLiveDataProgress.setValue(false);
                    Log.d(TAG, "onResponse: "+response.body().size());
                    mutableLiveDataPost.setValue(response.body());
                }

            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                mutableLiveDataProgress.setValue(false);
                Log.d(TAG, "Error Message Request onFailure: "+t.getMessage()+" "+ call.request().toString() );


            }
        });

        return mutableLiveDataPost;


    }

    public MutableLiveData<Boolean> getProgressObserve(){

        return mutableLiveDataProgress;
    }
}
