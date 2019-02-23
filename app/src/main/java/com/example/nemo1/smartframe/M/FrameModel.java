package com.example.nemo1.smartframe.M;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class FrameModel {
    private SendPresenter sendPresenter;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private DatabaseReference databaseReferenceImages;
    private List<String> urlList;
    private FirebaseAuth firebaseAuth;

    public FrameModel(SendPresenter sendPresenter) {
        this.sendPresenter = sendPresenter;
    }

    public void GetLoginFirebase(){
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword("signature8100@gmail.com","123456").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d("Logged-in","signature8100");
                GetData();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("NotLogin",e.getMessage());
            }
        });
    }

    public void GetData(){
        urlList = new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        databaseReferenceImages = databaseReference.child("images");
        databaseReferenceImages.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot getURLs:dataSnapshot.getChildren()){
                    urlList.add(getURLs.getValue().toString());
                }
                sendPresenter.onSendPresenter(urlList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Xemloi",databaseError.getMessage());
            }
        });
    }
}
