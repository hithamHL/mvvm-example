package com.hithamhl.faceboov.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hithamhl.faceboov.R;
import com.hithamhl.faceboov.pojo.PostModel;

import java.util.List;

public class PostAdapter extends

        RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private static final String TAG = PostAdapter.class.getSimpleName();

    private Context context;

    private List<PostModel> list;

    private OnItemClickListener onItemClickListener;

    public PostAdapter(Context context, List<PostModel> list,

                       OnItemClickListener onItemClickListener) {

        this.context = context;

        this.list = list;

        this.onItemClickListener = onItemClickListener;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView postId,userId,title;

        public ViewHolder(View itemView) {

            super(itemView);
            postId=itemView.findViewById(R.id.postId);
            userId=itemView.findViewById(R.id.postUserId);
            title=itemView.findViewById(R.id.postTitle_txt);

        }

        public void bind(final PostModel model,

                         final OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override

                public void onClick(View v) {

                    listener.onItemClick(getLayoutPosition());

                }

            });

        }

    }

    @Override

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.post_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;

    }

    @Override

    public void onBindViewHolder(ViewHolder holder, int position) {

        PostModel item = list.get(position);

        holder.bind(item, onItemClickListener);

        holder.userId.setText("User ID: "+item.getUserId());
        holder.postId.setText("Post ID: "+item.getId());

        if(item.isCompleted()){
            //set Line over text
            holder.title.setPaintFlags(holder.title.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
            holder.title.setText(item.getTitle());
        }else {
            holder.title.setText(item.getTitle());
        }

    }

    @Override

    public int getItemCount() {

        return list.size();

    }

    public interface OnItemClickListener {

        void onItemClick(int position);

    }

}