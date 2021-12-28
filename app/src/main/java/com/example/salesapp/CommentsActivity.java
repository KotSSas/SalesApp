package com.example.salesapp;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salesapp.adapter.ShopAdapter;
import com.example.salesapp.adapter.UserAdapter;
import com.example.salesapp.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CommentsActivity extends AppCompatActivity {
    FloatingActionButton button;
    EditText editText;
    TextView text;
    RecyclerView rc;
    FirebaseAuth mauth;
    UserAdapter userAdapter;
    List<User> itemList;
    RatingBar rb;
    ShopPage shopPage=new ShopPage();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_comm);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        button = findViewById(R.id.buttonApply);
        editText = findViewById(R.id.edMessage);
        rc = findViewById(R.id.rcView);
        rb= findViewById(R.id.ratingBar);
        mauth = FirebaseAuth.getInstance();
        //setUpActionBar();
        button.setOnClickListener(view -> {
            myRef.child(Objects.requireNonNull(myRef.push().getKey())).setValue(
                    new User(Objects.requireNonNull(mauth.getCurrentUser())
                            .getDisplayName(),editText.getText().toString(),
                            (int)rb.getRating()));

            Toast.makeText(this, "Message successfully sent!", Toast.LENGTH_SHORT).show();
            editText.getText().clear();

        });
        onChangeListener(myRef);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.signout) {
            mauth.signOut();
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void onChangeListener(DatabaseReference dRef) {
        dRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                 itemList= new ArrayList<>();
                for (DataSnapshot s : snapshot.getChildren()) {
                    User user = s.getValue(User.class);
                    if (user!=null){
                        itemList.add(user);
                    }
                }

                setItemRecycler(itemList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setUpActionBar() {
        ActionBar ab = getSupportActionBar();
//thread - поток

        Runnable runnable = () -> {
            try {
                Bitmap bitmap = Picasso.get().load(mauth.getCurrentUser().getPhotoUrl()).get();

                Drawable d = new BitmapDrawable(getResources(), bitmap);

                Runnable runnable1 = new Runnable() {
                    @Override
                    public void run() {
                        Objects.requireNonNull(ab).setDisplayHomeAsUpEnabled(true);
                        ab.setHomeAsUpIndicator(d);
                        ab.setTitle("Welcome back, " + mauth.getCurrentUser().getDisplayName());
                    }
                };
                runOnUiThread(runnable1);
            } catch (IOException e) {
                e.printStackTrace();
            }


        };
        Thread thread = new Thread(runnable);
        thread.start();

    }

    private void setItemRecycler(List<User> itemList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        rc = findViewById(R.id.rcView);
        rc.setLayoutManager(layoutManager);

        userAdapter = new UserAdapter(this, itemList);
        rc.setAdapter(userAdapter);
    }
}