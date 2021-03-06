package com.example.taskaway;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Acts as activity that displays a tasks information when a user selects a task
 * that they HAVE NOT previously bid on.
 *
 * @author Diane Boytang, Edited by Katherine Mae Patenio, Adrian Schuldhaus
 *
 * @see AllBids
 * @see MyBids
 */

public class ViewTask extends AppCompatActivity {

    // Views
    private TextView taskname;
    private TextView taskstatus;
    private TextView taskdescription;
    private TextView tasklocation;
    private TextView taskwinningbid;
    private TextView taskusername;
    private EditText userbid;
    private TextView yourPrice;
    private Button photos;
    private RelativeLayout saveButton;
    ArrayList<String> pictures = new ArrayList<String>();
    ArrayList<byte[]> arrayB = new ArrayList<byte[]>();
    private Task task;
    private Bid winningbid;
    private float bidamount;
    private ArrayList<Bid> bidList;
    private ImageButton toolBarBackbtn;
    private TextView toolBarTitle;
    private ImageButton toolBarSaveBtn;

    String id;
    String userID;
    String userName;


    /**
     * Creates TextView, EditView, and Button layouts. Also determines button behaviour.
     *
     * @param savedInstanceState - saved state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewtask);

        /* REMOVE TOOLBAR SAVE BUTTON FOR THIS ACTIVITY */
        toolBarSaveBtn = (ImageButton)findViewById(R.id.toolbar_save_btn);
        toolBarSaveBtn.setVisibility(View.GONE);

        /* REMOVE TOOLBAR BACK BUTTON FOR THIS ACTIVITY */
        toolBarSaveBtn = (ImageButton)findViewById(R.id.toolbar_back_btn);
        toolBarSaveBtn.setVisibility(View.GONE);

        /* SET TITLE OF TOOLBAR */
        toolBarTitle = (TextView)findViewById(R.id.toolbar_title);
        toolBarTitle.setText("View Task");

        // Text layouts
        taskname = (TextView) this.findViewById(R.id.viewtask_name);
        taskdescription = (TextView) this.findViewById(R.id.viewtask_requirementsk);
        taskstatus = (TextView) this.findViewById(R.id.viewtask_status);
        tasklocation = (TextView) this.findViewById(R.id.viewtask_loc_textview);
        taskwinningbid = (TextView) this.findViewById(R.id.viewtask_lowestbid);
        userbid = (EditText) this.findViewById(R.id.viewtask_newbid);
        taskusername = (TextView) this.findViewById(R.id.viewtask_username);
        yourPrice = (TextView) this.findViewById(R.id.taskview_yourprice);
        photos = (Button) this.findViewById(R.id.viewtask_photobtn);

        photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pictures = task.getPictures();
                if (pictures != null) {
                    Intent intent = new Intent(ViewTask.this, PhotosViewOwnTask.class);
                    intent.putExtra("userName", userName);
                    intent.putExtra("userid",userID);
                    intent.putExtra("task", task);
                    intent.putExtra("viewTaskType","ViewTask");

                    for (int n = 0; n < pictures.size(); n++) {
                        byte[] encodeByte = Base64.decode(pictures.get(n), Base64.DEFAULT);
                        //Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
                        arrayB.add(encodeByte);
                    }
                    intent.putExtra("byteArraySize", arrayB.size());
                    for (int i = 0; i < arrayB.size(); i++) {
                        intent.putExtra("barray"+i, arrayB.get(i));
                        Log.i("UPLOAD", "barray(i)"+arrayB.get(i));
                    }
                    startActivity(intent);
                }
                else {
                    Log.i("ADDTASK"," null!");
                    return;
                }

            }
        });


        // SAVE BUTTON - place a bid
        saveButton = (RelativeLayout) findViewById(R.id.taskview_savebtn);
        saveButton.setOnClickListener(new View.OnClickListener() {

            /**
             * Allows user to place a bid on another user's task once the save button is selected.
             * Updates list of bids for a certain task for task requester.
             * Updates list of tasks task provider has bidded on
             *\
             * @param view - instance of View
             * @author Katherine Mae Patenio, Diane Boytang
             * @see SaveFileController
             * @see MyBids
             */
            @Override
            public void onClick(View view) {
                String inputbid = userbid.getText().toString();

                if (!task.allowsBids()){
                    Toast.makeText(getApplicationContext(), "This job is no longer accepting bids", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check if input is valid
                try {
                    // No bid entered
                    if (inputbid.isEmpty()) {
                        userbid.setError("No bid entered!");
                        return;
                    }

                    bidamount = Float.parseFloat(inputbid);

                    // Not a decimal/float
                } catch (Exception e) {
                    Log.i("ViewTask", "Invalid bid entered!");
                    userbid.setError("Invalid bid entered!");
                    return;
                } // end of catch

                /* ADDING NEW BID STARTS HERE */
                Bid bid = new Bid(userID, bidamount);

                // If no bid list exists for a task, create a new one
                if (task.getBids() == null) {
                    Log.i("ViewTask", "task.getBids() is NULL");

                    // Make new arraylist to contain bids
                    bidList = new ArrayList<Bid>();
                    bidList.add(bid);
                    task.setBids(bidList);


                }
                // Else, update current one
                else {
                    Log.i("ViewTask", "task.getBids() is NOT NULL");

                    bidList = task.getBids();
                    bidList.add(bid);
                    task.setBids(bidList);
                }


                // Update task to bidded
                if (MainActivity.isOnline()){
                    //Set information for notifications
                    task.setHasNewBids(true);
                    task.setStatus("BIDDED");
                    ServerWrapper.updateJob(task);
                    Log.i("ViewBidTask", "Updating Task on server");
                }

                // GO BACK TO MAIN
                Intent intent2 = new Intent(ViewTask.this, MainActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent2.putExtra("user_name", userName);
                intent2.putExtra("user_id", userID);
                Log.i("ViewTask", "Sending name and id to MainActivity!");
                startActivity(intent2);
            }
        });

        /* PROFILE OPENED WHEN SELECTING USERNAME */
        TextView requestUser = (TextView) findViewById(R.id.viewtask_username);
        requestUser.setOnClickListener(new View.OnClickListener(){

            /**
             *
             * When task requester username is selected, their profile shows up
             *
             * @author Punam Woosaree
             */
           @Override
           public void onClick(View view) {

               User requesterUser = ServerWrapper.getUserFromId(task.getCreatorId());

               //Sending info to ViewOtherProfile
               Intent intent = new Intent(ViewTask.this, ViewOtherProfile.class);
               intent.putExtra("user_name", requesterUser.getUsername());
               intent.putExtra("user_id", requesterUser.getId());
               Log.i("ViewTask", "Sending name and id to ViewOtherProfile!");
               startActivity(intent);
           }

        });

        // CANCEL BUTTON - cancel activity
        RelativeLayout cancelButton = (RelativeLayout) findViewById(R.id.taskview_cancelbtn);
        cancelButton.setOnClickListener(new View.OnClickListener() {

            /**
             * Cancels activity when cancel button selected by user.
             *
             * @param view - instance of View
             */
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewTask.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("user_name", userName);
                intent.putExtra("user_id", userID);
                //Log.i("AddActivity","Sending name and id to MainActivity!");
                startActivity(intent);
            }
        });

        //Location Details
        ImageButton locationButton = (ImageButton) findViewById(R.id.taskview_loaction_detailbtn);
        locationButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Begins MapActivity when the user selects the Location Details button.
             *
             * @author Diane Boytang
             *
             * @param view - instance of View
             *
             * @see MapActivity
             * @see SaveFileController
             */
            @Override
            public void onClick(View view) {

                // Get task information
                Intent intent = new Intent(getBaseContext(), MapActivity.class);
                String name = taskname.getText().toString();
                String location = tasklocation.getText().toString();

                // If no location is entered by the user
                if(location.equals("N/A")){
                    return;
                }

                id = task.getId();

                // Pass relevant information to MapActivity via SaveFileController
                final Context context = getApplicationContext();
                final SaveFileController saveFileController = new SaveFileController();
                final int userIndex = saveFileController.getUserIndex(context, userName);

                // Send task info
                String index = Integer.toString(userIndex);
                intent.putExtra("name", name);
                intent.putExtra("location", location);
                intent.putExtra("task_id", id);


                // Send user info
                intent.putExtra("userid", userID);
                intent.putExtra("userName", userName);

                startActivity(intent);
            }
        });
    }

    /**
     * Displays task information and updates layout. Also receive username and user id.
     *
     */
    @Override
    protected void onStart(){
        super.onStart();
        Log.i("ViewTask","Viewing task!");

        // Get task information
        Intent intent = getIntent();
        task = (Task) intent.getSerializableExtra("task");

        //change new bids to false
        if(task.hasNewBids()){
            task.setHasNewBids(false);
        }


        Log.i("ViewTask","Now reading task id: "+task.getId());

        // Read and display task info
        taskname.setText(task.getName());
        taskstatus.setText(task.getStatus());
        tasklocation.setText(task.getLocation());
        taskdescription.setText(task.getDescription());

        /* DISPLAY REQUESTER INFO */
        User requester;
        if (MainActivity.isOnline()){ // online
            requester = ServerWrapper.getUserFromId(task.getCreatorId());
        }else{ // offline
            SaveFileController saveFileController = new SaveFileController();
            requester = saveFileController.getUserFromUserId(getApplicationContext(), task.getCreatorId());
        }

        String requestername;

        // Display name of requester
        if (requester != null){
            requestername = requester.getUsername();
        }else{
            requestername = "UNKNOWN";
            Toast.makeText(getApplicationContext(), "An error occurred when resolving job creator's username", Toast.LENGTH_SHORT).show();
        }
        taskusername.setText(requestername);

        // Get current user information
        userID = intent.getStringExtra("userid");
        userName = intent.getStringExtra("userName"); // FIXME username vs userName

        /* HIDE CERTAIN OPTIONS DEPENDING ON WHO VIEWS THE TASK */
        // If this task was created by the viewer, hide bid options
        if (userID.equals(task.getCreatorId())||!task.allowsBids()){
            CardView cv_ingredient = (CardView) findViewById(R.id.taskview_cardview2);
            cv_ingredient.setVisibility(View.GONE);
            //saveButton.setVisibility(View.INVISIBLE);
            userbid.setVisibility(View.INVISIBLE);
            yourPrice.setVisibility(View.INVISIBLE);
        }else{
            saveButton.setVisibility(View.VISIBLE);
            userbid.setVisibility(View.VISIBLE);
            yourPrice.setVisibility(View.VISIBLE);
        }

        // Bid information
        try {
            if (task.getBids().isEmpty()) {
                taskwinningbid.setText("No bids yet!");
            } else {
                winningbid = task.findLowestBid();
                taskwinningbid.setText(String.format("$%.2f", winningbid.getAmount()));

            }
        }
        catch (Exception e){
            taskwinningbid.setText("No bids yet!");
        }
    }
}
