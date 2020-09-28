package com.hithamhl.faceboov.ui.main;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hithamhl.faceboov.pojo.PostModel;

import java.util.List;

public class PostViewModel extends ViewModel {
    private static final String TAG = "PostViewModel";

    PostRepository postRepository;

    MutableLiveData<List<PostModel>> mutableLiveData;
    MutableLiveData<Boolean> booleanProgress;

    public PostViewModel() {
        postRepository=new PostRepository();

    }


    public LiveData<List<PostModel>> getAllPost(){
        if (mutableLiveData==null){
            mutableLiveData=postRepository.getMutableLiveDataPost();
           // Log.d(TAG, "getAllPost: "+mutableLiveData.getValue().size());
        }



        return mutableLiveData;
    }


    public LiveData<Boolean> getProgressShow(){

        if (booleanProgress==null){
            booleanProgress=postRepository.getProgressObserve();
            Log.d(TAG, "getProgressShow: "+getProgressShow().getValue());
        }

        return booleanProgress;

    }




}
