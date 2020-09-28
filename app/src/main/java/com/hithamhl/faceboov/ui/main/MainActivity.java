package com.hithamhl.faceboov.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.hithamhl.faceboov.R;
import com.hithamhl.faceboov.adapter.PostAdapter;
import com.hithamhl.faceboov.databinding.ActivityMainBinding;
import com.hithamhl.faceboov.pojo.PostModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    ActivityMainBinding activityMainBinding;
    //set
    PostViewModel postViewModel;

    PostAdapter postAdapter;

    List<PostModel> postModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);


        //viewModelProviders is deprecated
       // postViewModel=new ViewModelProvider(this).get(PostViewModel.class);
        postViewModel =new PostViewModel();
        postModelList=new ArrayList<>();




        postViewModel.getAllPost().observe(this, new Observer<List<PostModel>>() {
            @Override
            public void onChanged(List<PostModel> postModels)
            {

                postModelList.addAll(postModels);
                postAdapter.notifyDataSetChanged();
                Log.d(TAG, "onChanged:Postlist "+postModels.size());
            }
        });

        Log.d(TAG, "onCreate: arrayPostSize: "+postModelList.size());

        postViewModel.getProgressShow().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    activityMainBinding.progressShow.setVisibility(View.VISIBLE);
                    Log.d(TAG, "onChanged:Progress "+aBoolean);
                }else {
                    activityMainBinding.progressShow.setVisibility(View.GONE);
                    Log.d(TAG, "onChanged:Progress after "+aBoolean);
                }
            }
        });

        postAdapter=new PostAdapter(this, postModelList, new PostAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
        Log.d(TAG, "onCreate: arrayPostSize: affter"+postModelList.size());

        //init ui
        activityMainBinding.postRecycleView.setLayoutManager(new LinearLayoutManager(this));
        activityMainBinding.postRecycleView.setAdapter(postAdapter);







    }
}
