package com.example.taskaway;

import java.util.ArrayList;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by PunamWoosaree on 2018-02-21.
 */


/**
 * Tests all methods contained in the Task class
 */
public class TestTask extends ActivityInstrumentationTestCase2{
    public TestTask() {super(Task.class);}

    /**
     * Test the constructor
     */
    public void testTask(){
        ArrayList<Bid> bidsList = new ArrayList<>();
        ArrayList<Task> picturesList = new ArrayList<>();
        Float lowestBid = 15.00f;

        Task task = new Task("name", "I'm a task", "requested", "Edmonton", bidsList, picturesList, lowestBid);

        assertEquals(task.getName(), "name");
        assertEquals(task.getDescription(), "I'm a task");
        assertEquals(task.getStatus(), "requested");
        assertEquals(task.getLocation(), "Edmonton");
        assertEquals(task.getLowestBid(), 15.00);
        assertEquals(task.getBids(), bidsList);
        assertEquals(task.getPictures(), picturesList);

    }

    /**
     * Test getName()
     */

    public void testGetName(){
        ArrayList<Bid> bidsList = new ArrayList<>();
        ArrayList<Task> picturesList = new ArrayList<>();
        Float lowestBid = 15.00f;

        Task task = new Task("name", "I'm a task", "requested", "Edmonton", bidsList, picturesList, lowestBid);
        assertEquals(task.getName(), "name");
    }

    /**
     * Test getDescription()
     */
    public void testGetDescription(){
        ArrayList<Bid> bidsList = new ArrayList<>();
        ArrayList<Task> picturesList = new ArrayList<>();
        Float lowestBid = 15.00f;

        Task task = new Task("name", "I'm a task", "requested", "Edmonton", bidsList, picturesList, lowestBid);
        assertEquals(task.getDescription(), "I'm a task");
    }

    /**
     * Test getStatus()
     */
    public void testGetStatus(){
        ArrayList<Bid> bidsList = new ArrayList<>();
        ArrayList<Task> picturesList = new ArrayList<>();
        Float lowestBid = 15.00f;

        Task task = new Task("name", "I'm a task", "requested", "Edmonton", bidsList, picturesList, lowestBid);
        assertEquals(task.getStatus(), "requested");
    }

    /**
     * Test getLocation()
     */
    public void testGetLocation(){
        ArrayList<Bid> bidsList = new ArrayList<>();
        ArrayList<Task> picturesList = new ArrayList<>();
        Float lowestBid = 15.00f;

        Task task = new Task("name", "I'm a task", "requested", "Edmonton", bidsList, picturesList, lowestBid);
        assertEquals(task.getLocation(), "Edmonton");
    }

    /**
     * Test getLowestBid()
     */
    public void testGetLowestBid(){
        ArrayList<Bid> bidsList = new ArrayList<>();
        ArrayList<Task> picturesList = new ArrayList<>();
        Float lowestBid = 15.00f;

        Task task = new Task("name", "I'm a task", "requested", "Edmonton", bidsList, picturesList, lowestBid);
        assertEquals(task.getLowestBid(), 15.00);
    }

    /**
     * Test getBids()
     */
    public void testGetBids(){
        ArrayList<Bid> bidsList = new ArrayList<>();
        ArrayList<Task> picturesList = new ArrayList<>();
        Float lowestBid = 15.00f;

        Task task = new Task("name", "I'm a task", "requested", "Edmonton", bidsList, picturesList, lowestBid);
        assertEquals(task.getBids(), bidsList);
    }

    /**
     * Test getPictures()
     */
    public void testGetPictures(){
        ArrayList<Bid> bidsList = new ArrayList<>();
        ArrayList<Task> picturesList = new ArrayList<>();
        Float lowestBid = 15.00f;

        Task task = new Task("name", "I'm a task", "requested", "Edmonton", bidsList, picturesList, lowestBid);
        assertEquals(task.getPictures(), picturesList);
    }

    /**
     * Test setName()
     */

    public void testSetName(){
        ArrayList<Bid> bidsList = new ArrayList<>();
        ArrayList<Task> picturesList = new ArrayList<>();
        Float lowestBid = 15.00f;

        Task task = new Task("name", "I'm a task", "requested", "Edmonton", bidsList, picturesList, lowestBid);
        String newName = "new name";
        task.setName(newName);

        assertEquals(task.getName(), "new name");
    }

    /**
     * Test setDescription()
     */
    public void testSetDescription(){
        ArrayList<Bid> bidsList = new ArrayList<>();
        ArrayList<Task> picturesList = new ArrayList<>();
        Float lowestBid = 15.00f;

        Task task = new Task("name", "I'm a task", "requested", "Edmonton", bidsList, picturesList, lowestBid);
        String newDescription = "I'm a description";
        task.setDescription(newDescription);

        assertEquals(task.getDescription(), "I'm a description");
    }

    /**
     * Test setStatus()
     */
    public void testSetStatus(){
        ArrayList<Bid> bidsList = new ArrayList<>();
        ArrayList<Task> picturesList = new ArrayList<>();
        Float lowestBid = 15.00f;

        Task task = new Task("name", "I'm a task", "requested", "Edmonton", bidsList, picturesList, lowestBid);
        String newStatus= "bidded";
        task.setStatus(newStatus);

        assertEquals(task.getStatus(), "bidded");
    }

    /**
     * Test setLocation()
     */
    public void testSetLocation(){
        ArrayList<Bid> bidsList = new ArrayList<>();
        ArrayList<Task> picturesList = new ArrayList<>();
        Float lowestBid = 15.00f;

        Task task = new Task("name", "I'm a task", "requested", "Edmonton", bidsList, picturesList, lowestBid);
        String newLocation = "Calgary";
        task.setLocation(newLocation);
        assertEquals(task.getLocation(), "Calgary");
    }

    /**
     * Test setLowestBid()
     */
    public void testSetLowestBid(){
        ArrayList<Bid> bidsList = new ArrayList<>();
        ArrayList<Task> picturesList = new ArrayList<>();
        Float lowestBid = 15.00f;

        Task task = new Task("name", "I'm a task", "requested", "Edmonton", bidsList, picturesList, lowestBid);
        Float newLowestBid = 7.00f;
        task.setLowestBid(newLowestBid);
        assertEquals(task.getLowestBid(), 7.00);
    }

    /**
     * Test setBids()
     */
    public void testSetBids(){
        ArrayList<Bid> bidsList = new ArrayList<>();
        ArrayList<Task> picturesList = new ArrayList<>();
        Float lowestBid = 15.00f;

        Task task = new Task("name", "I'm a task", "requested", "Edmonton", bidsList, picturesList, lowestBid);
        ArrayList<Bid> newBidsList = new ArrayList<>();
        task.setBids(newBidsList);
        assertEquals(task.getBids(), newBidsList);
    }

    /**
     * Test setPictures()
     */
    public void testSetPictures(){
        ArrayList<Bid> bidsList = new ArrayList<>();
        ArrayList<Task> picturesList = new ArrayList<>();
        Float lowestBid = 15.00f;

        Task task = new Task("name", "I'm a task", "requested", "Edmonton", bidsList, picturesList, lowestBid);
        ArrayList<Task> newPicturesList = new ArrayList<>();
        task.setPictures(newPicturesList);
        assertEquals(task.getPictures(), newPicturesList);
    }

}