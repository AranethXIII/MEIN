package com.example.myapplication.Firestore;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


public class FirestoreWrapper<T> {

    public final static int INITIALIZED = 0;
    public final static int PENDING = 1;
    public final static int SUCCESS = 2;
    public final static int ERROR = 3;

    /**
     * Default constructor
     */
    public FirestoreWrapper() {
        this(null);
    }

    /**
     * Constructor which gets the object
     *
     * @param data the Object
     */
    public FirestoreWrapper(T data) {
        status.postValue(INITIALIZED);
        tData.postValue(data);
    }


    /**
     * Getter for the status of the wrapper, which depends on the current action
     *
     * @return Integer, which represents the status of the wrapper
     * '0' if the wrapper has been created,
     * '1' if the action is still working,
     * '2' if the action was a success and
     * '3' if an error occurred
     */
    public LiveData<Integer> getStatus() {
        return status;
    }

    /**
     * Getter for the data object
     *
     * @return Object with the current state of the data
     */
    public LiveData<T> getData() {
        return tData;
    }

    /**
     * Setter for the data which extends
     *
     * @param data Object with the new data
     */
    public void setData(T data) {
        tData.setValue(data);
    }

    /**
     * Setter for the data which extends, must be called when in a sub-thread
     *
     * @param data Object with the new data
     */
    public void postData(T data) {
        tData.postValue(data);
    }

    /**
     * Sets the current status of the wrapper on pending, this one is only callable from the UI Thread
     */
    void setPending() {
        status.setValue(PENDING);
    }

    /**
     * Sets the current status of the wrapper on pending
     */
    void postPending() {
        status.postValue(PENDING);
    }

    /**
     * Sets the current state of the wrapper on success
     */
    void postSuccess() {
        status.postValue(SUCCESS);
    }

    /**
     * Sets the current state of the wrapper on error
     */
    void postError() {
        status.postValue(ERROR);
    }


    private final MutableLiveData<Integer> status = new MutableLiveData<Integer>();
    private final MutableLiveData<T> tData = new MutableLiveData<T>();
}