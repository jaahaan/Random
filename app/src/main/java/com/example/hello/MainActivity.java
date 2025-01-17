package com.example.hello;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hello.A.A;
import com.example.hello.B.B;
import com.example.hello.C.C;
import com.example.hello.D.D;
import com.example.hello.activities.BirthdayCardActivity;
import com.example.hello.activities.ButtonActivity;
import com.example.hello.activities.CalculatorActivity;
import com.example.hello.activities.FormActivity;
import com.example.hello.activities.OrderActivity;
import com.example.hello.crud.CreateActivity;
import com.example.hello.crud.ReadActivity;
import com.example.hello.recyclerView.RecyclerViewActivity;
import com.example.hello.auth.SignInActivity;
import com.example.hello.activities.StatusBar;
import com.example.hello.listView.ListViewActivity;
import com.example.hello.activities.WebViewActivity;
import com.example.hello.tech.TechListActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class MainActivity extends AppCompatActivity {

    Button button, card, order, statusBar, calculator, form, listView, recycler, website, tech, signOut;
    private static final String TAG = "lifecycle";

    FirebaseUser user;
    TextView name, email;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Log.d(TAG,"onCreate called");

        button = findViewById(R.id.button);
        card = findViewById(R.id.card);
        order = findViewById(R.id.order);
        statusBar = findViewById(R.id.status_bar);
        calculator = findViewById(R.id.calculator);
        form = findViewById(R.id.form);
        website = findViewById(R.id.website);
        listView = findViewById(R.id.listViewActivity);
        recycler = findViewById(R.id.recyclerViewActivity);
        tech = findViewById(R.id.rand);
        signOut = findViewById(R.id.signOut);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        auth = FirebaseAuth.getInstance();
        user= auth.getCurrentUser();
//        firestore = FirebaseFirestore.getInstance();
        assert user != null;
        DocumentReference reference = FirebaseFirestore.getInstance().collection("Users").document(user.getUid());
        reference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value != null && value.exists()) {
                    name.setText(value.getString("name"));
                    email.setText(value.getString("email"));
                }
            }
        });


        button.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), ButtonActivity.class)));
        card.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), BirthdayCardActivity.class)));
        order.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), OrderActivity.class)));
        statusBar.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), StatusBar.class)));
        calculator.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), CalculatorActivity.class)));
        form.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), FormActivity.class)));
        listView.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), ListViewActivity.class)));
        recycler.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), RecyclerViewActivity.class)));
        website.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), B.class)));
        tech.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), ReadActivity.class)));
        signOut.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), SignInActivity.class));
            finish();
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart called");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume called");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause called");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop called");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"onRestart called");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy called");
    }
}

