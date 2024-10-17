package com.example.logisticcavan.auth.data;

import android.util.Log;

import com.example.logisticcavan.auth.domain.repo.AuthRepository;
import com.example.logisticcavan.auth.domain.entity.RegistrationData;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.CompletableFuture;

public class AuthRepoImp implements AuthRepository {

    private FirebaseAuth firebaseAuth;

    public AuthRepoImp(FirebaseAuth firebaseAuth) {
        this.firebaseAuth = firebaseAuth;
    }

    @Override
    public CompletableFuture<AuthResult> logIn(RegistrationData registrationData) {
        CompletableFuture<AuthResult> future = new CompletableFuture<>();

        firebaseAuth.signInWithEmailAndPassword(registrationData.getEmail(), registrationData.getPassword())
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        future.complete(task.getResult());
                    } else {
                        future.completeExceptionally(task.getException());
                    }
                });

        return future;
    }


    @Override
    public CompletableFuture<AuthResult> signUp(RegistrationData registrationData) {
        CompletableFuture<AuthResult> future = new CompletableFuture<>();

        firebaseAuth.createUserWithEmailAndPassword(registrationData.getEmail(), registrationData.getPassword())
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        future.complete(task.getResult());
                    } else {
                        future.completeExceptionally(task.getException());
                    }
        });

        return future;

    }

    @Override
    public CompletableFuture<Void> sendPasswordResetEmail(String email) {
        Log.e("TAG", "sendPasswordResetEmail: " );

        CompletableFuture<Void> future = new CompletableFuture<>();

     Task<Void> task = firebaseAuth.sendPasswordResetEmail(email);
     task.addOnCompleteListener( task1 ->{
         if (task1.isSuccessful()){
             Log.e("TAG", "sendPasswordResetEmail: null  " );

             future.complete(task1.getResult());
         }else {
             Log.e("TAG", "sendPasswordResetEmail: getException " );

             future.completeExceptionally(task1.getException());
         }
     }) ;
        return null;
    }


}
