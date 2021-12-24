package com.horseman.flamingo;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.Queue;
import java.util.zip.Inflater;

public class chatsFragment extends Fragment {
    // 1st add dependencies     implementation 'com.firebaseui:firebase-ui-firestore:4.1.0' thorough we can directly call firebase recycler view
    private FirebaseFirestore firebaseFirestore;
    LinearLayoutManager linearLayoutManager;
    private FirebaseAuth firebaseAuth;

    ImageView imageViewOfUser;
    RecyclerView recyclerView;

    FirestoreRecyclerAdapter<FirebaseModel, NoteViewHolder> chatAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chats_fragment, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        recyclerView = view.findViewById(R.id.recyclerView);

        Query query = firebaseFirestore.collection("Users");
        FirestoreRecyclerOptions<FirebaseModel> allUser = new FirestoreRecyclerOptions.Builder<FirebaseModel>().setQuery(query, FirebaseModel.class).build();

        chatAdapter = new FirestoreRecyclerAdapter<FirebaseModel, NoteViewHolder>(allUser) {
            @Override
            protected void onBindViewHolder(@NonNull NoteViewHolder noteViewHolder, int i, @NonNull FirebaseModel firebaseModel) {

                noteViewHolder.particularUserName.setText(firebaseModel.getName());

                String uri = firebaseModel.getImage();
                Glide.with(chatsFragment.this).load(uri).into(imageViewOfUser);

                if (firebaseModel.getStatus().equals("online")){
                        noteViewHolder.statusOfUser.setText(firebaseModel.getStatus());
                        noteViewHolder.statusOfUser.setTextColor(Color.GREEN);
                }else{
                    noteViewHolder.statusOfUser.setText(firebaseModel.getStatus());

                }
                noteViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "yes", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @NonNull
            @Override
            public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view1 = LayoutInflater.from(getContext()).inflate(R.layout.chatviewlayout, parent, false);
                return new NoteViewHolder(view1);
            }
        };
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(chatAdapter);

        return view;
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView particularUserName, statusOfUser;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            particularUserName = itemView.findViewById(R.id.nameOfUser);
            statusOfUser = itemView.findViewById(R.id.statusOfUser);
            imageViewOfUser = itemView.findViewById(R.id.getUserImageInCVIV);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        chatAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (chatAdapter!=null){
            chatAdapter.startListening();
        }
    }
}
