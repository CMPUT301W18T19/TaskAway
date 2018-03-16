package com.example.taskaway;

import java.util.ArrayList;

/**
 * Created by sameerah on 18/02/18.
 */

/**
 * Represents a list of tasks.
 */
public class TaskList extends ArrayList<Task> {

    /**
     * Adds a new task to list of tasks.
     * @param task - task to be added
     */
    public void addTask(Task task){
        super.add(task);
    }

    /**
     * Checks if a task currently exists in list of tasks.
     * @param task - task to check if it exists
     * @return - boolean value of True or False - True if task exists; otherwise False
     */
    public boolean hasTask(Task task){
        return super.contains(task);
    }

    /**
     * Returns a task from list of tasks.
     * @param index - index of the task to be returned
     * @return - task at current index
     */
    public Task getTask(int index){
        return super.get(index);
    }

    /**
     * Deletes a task from list of tasks.
     * @param task - task to be deleted
     */
    public void removeTask(Task task){
        super.remove(task);
    }

}
