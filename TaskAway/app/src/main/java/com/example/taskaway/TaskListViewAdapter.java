package com.example.taskaway;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Displays appropriate data when user goes go view current requested tasks (MyJobs).
 * Used in conjuction with the item_jobs.xml layout.
 * As each item in the layout holds a specifc Textview position, we are able to change the data accordingly.
 *
 * @author Jonathan Ismail
 *
 * @see MyJobs
 * @see MainActivity
 * @see RecyclerView.Adapter
 * @see ViewOwnTask
 */
public class TaskListViewAdapter extends RecyclerView.Adapter<TaskListViewAdapter.MyViewHolder> {

    Context mContext;
    ArrayList<Task> mData;
    String userName;
    String userID;


    /**
     * Constructor of TaskListViewAdapter
     * @param mContext - current context; instance of Context
     * @param mData - data containing appropriate information (user's tasks)
     * @param userName - current user's username
     * @param userID - current user's id
     */
    public TaskListViewAdapter(Context mContext, ArrayList<Task> mData, String userName, String userID){
        this.mContext = mContext;
        this.mData = mData;
        this.userName = userName;
        this.userID = userID;
    }

    /**
     * Creates View Holder of TaskListViewAdapter.
     * Also determines behaviour when a task is selected by user.
     *
     * @param parent
     * @param viewType
     * @return MyViewHolder
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_jobs,parent, false);

        final MyViewHolder vHolder = new MyViewHolder(v);

        vHolder.item.setOnClickListener(new View.OnClickListener() {

            /**
             * View current requested task when task is selected. Go to ViewOwnTask activity.
             * Also pass username and userid to ViewOwnTask.
             *
             * @param view
             *
             * @see ViewOwnTask
             */
            @Override
            // Go to a requester's own task
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ViewOwnTask.class);
                int pos = vHolder.getAdapterPosition();
                intent.putExtra("task", mData.get(pos));
                intent.putExtra("userid", userID);
                intent.putExtra("userName", userName);
                String position = Integer.toString(pos);
                intent.putExtra("index", position);
                mContext.startActivity(intent);
            }
        });

        return vHolder;
    }

    /**
     * Gets item position and displays name of task (through TextView found in items_job.xml).
     *
     * @param holder - current holder created
     * @param position - position of task
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv_name.setText(mData.get(position).getName());
        holder.tv_img.setImageResource(R.mipmap.ic_workicon);
        //holder.img_status.setText(mTasks.getTask(position).getStatus());
        //holder.img_jobs.setText(mTasks.getTask(position).getPictures());
        holder.status_img.setImageResource(R.mipmap.ic_messengerbw);
        if (mData.get(position).hasNewBids() == true){
            holder.status_img.setImageResource(R.mipmap.ic_message_notification);
        }


    }

    /**
     * Get number of tasks to display.
     *
     * @return int - number of tasks to display
     */
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout item;
        private TextView tv_name;
        private ImageView tv_img;
        private ImageView status_img;
        //private TextView tv_status;
        //private TextView img; <- For images
        public MyViewHolder(View itemView) {
            super(itemView);
            item = (LinearLayout) itemView.findViewById(R.id.item_task);
            tv_name = (TextView) itemView.findViewById(R.id.name_jobs);
            tv_img = (ImageView) itemView.findViewById(R.id.img_jobs);
            status_img = (ImageView) itemView.findViewById(R.id.img_status);


        }
    }
}
