package com.example.myapplication.Firestore;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.util.Map;

public abstract class FirestoreConnection<T> {

    /**
     * saves the date from the wrapper into the db
     * @param wrapper wrapper with the data
     * @param id String with the id for the object in the db
     */
   protected void addDocument(final FirestoreWrapper<T> wrapper,String id){
      wrapper.postPending();
      firestore.collection(getCollectionPath()).add(wrapper.getData().getValue())
              .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                  @Override
                  public void onSuccess(DocumentReference documentReference) {
                      wrapper.postSuccess();
                  }
              })
              .addOnFailureListener(new OnFailureListener() {
                 @Override
                 public void onFailure(@NonNull Exception e) {
                    wrapper.postError();
                 }
              });
   }

    /**
     * loads document from database
     * @param wrapper wrapper-Object which will get the data
     * @param path getCollection() + id of the object in the db
     */
   protected void getDocument(final FirestoreWrapper<T> wrapper, String path){
      wrapper.postPending();
      firestore.document(path).get()
              .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                 @Override
                 public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot.exists() &&documentSnapshot.getData() !=null){
                       wrapper.setData(toObject(documentSnapshot.getData()));
                       wrapper.postSuccess();
                    }else{
                       wrapper.postError();
                    }
                 }
              })
              .addOnFailureListener(new OnFailureListener() {
                 @Override
                 public void onFailure(@NonNull Exception e) {
                    wrapper.postError();
                 }
              });
   }

   protected void setDocument(final FirestoreWrapper<T> wrapper, String path){
      wrapper.postPending();
      firestore.document(path).set(toMap(wrapper.getData().getValue()))
              .addOnSuccessListener(new OnSuccessListener<Void>() {
                 @Override
                 public void onSuccess(Void aVoid) {
                    wrapper.postSuccess();
                 }
              })
              .addOnFailureListener(new OnFailureListener() {
                 @Override
                 public void onFailure(@NonNull Exception e) {
                    wrapper.postError();
                 }
              });
   }

   public FirestoreConnection(){
      firestore=FirebaseFirestore.getInstance();
      FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
              .setTimestampsInSnapshotsEnabled(true)
              .build();
      firestore.setFirestoreSettings(settings);
   }
   protected FirebaseFirestore firestore;
   protected abstract String getCollectionPath();
   protected abstract Map<String, Object> toMap(@NonNull T objectToConvert);
   protected abstract T toObject(@NonNull Map<String, Object> objectMap);

}
