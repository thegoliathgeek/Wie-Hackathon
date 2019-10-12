package in.ac.sit.ec.sher_lock;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.EventListener;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    TextView imName;
    EditText unknownName;
    Button refresh, allowButton, denyButton, submitName;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private DatabaseReference image = databaseReference.child("image");
    private DatabaseReference someName = databaseReference.child("imName");
    private DatabaseReference allow = databaseReference.child("allow");
    private DatabaseReference knownParam = databaseReference.child("known");
    boolean known;

    static String link = "https://firebasestorage.googleapis.com/v0/b/badhackathon.appspot.com/o/known.png?alt=media";
    static String link2 = "https://firebasestorage.googleapis.com/v0/b/badhackathon.appspot.com/o/unknown.png?alt=media";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.img);
        imName = findViewById(R.id.imName);
        allowButton = findViewById(R.id.buttonAllow);
        denyButton = findViewById(R.id.buttonDeny);
        refresh = findViewById(R.id.buttonRefresh);
        unknownName = findViewById(R.id.unknownName);
        submitName = findViewById(R.id.submitName);

        allowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allow.child("value").setValue(true);
//                someName.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        HashMap<String,String> ob2;
//                        ob2 = (HashMap<String, String>) dataSnapshot.getValue();
//                        String value = ob2.get("value");
//                        try {
//                            if (value == null || value.isEmpty()) {
//                                unknownName.setVisibility(View.VISIBLE);
//                                submitName.setVisibility(View.VISIBLE);
//                            }
//                        } catch (NullPointerException e) {
//                            unknownName.setVisibility(View.VISIBLE);
//                            submitName.setVisibility(View.VISIBLE);
//                        }
//                    }
//
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });

            }
        });

        denyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allow.child("value").setValue(false);
            }
        });

//        submitName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String text = unknownName.getText().toString();
//
//                if (text.isEmpty())
//                    Toast.makeText(MainActivity.this, "Please enter valid name.", Toast.LENGTH_SHORT).show();
//                else {
//                    someName.child("value").setValue(text);
//                    unknownName.setVisibility(View.INVISIBLE);
//                    submitName.setVisibility(View.INVISIBLE);
//                }
//            }
//
//
//        });


        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                knownParam.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        known = dataSnapshot.getValue(boolean.class);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


                image.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

//                String link1 = dataSnapshot.getValue(String.class);
                        if (known)
                            Picasso.get().load(link).into(imageView);
                        else
                            Picasso.get().load(link2).into(imageView);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                someName.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String val2 = dataSnapshot.getValue(String.class);

                        System.out.println(val2);
//                try {
////                    if (val2 == null || val2.isEmpty())
////                        imName.setText("Unknown Person");
////                    else
////                        imName.setText(val2);
//                } catch (Exception e) {
//                    imName.setText("Unknown Person");
//                }
                        imName.setText(val2);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });









//                finish();
//                startActivity(getIntent());

//                Context context = getApplicationContext();
//                context.getCacheDir().deleteOnExit();
//
//
//                finish();
//                overridePendingTransition(0, 0);
//                startActivity(getIntent());
//                overridePendingTransition(0, 0);
//



//                someName.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        HashMap<String,String> ob1;
//                        ob1 = (HashMap<String, String>) dataSnapshot.getValue();
//                        String value = ob1.get("value");
//                        try {
//                            if (value.isEmpty())
//                                imName.setText("Unknown Person");
//                            else
//                                imName.setText(value);
//                        } catch (NullPointerException e) {
//                            imName.setText("Unknown Person");
//                        }
//
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
//
//                image.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        Picasso.get().load(link).into(imageView);
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
//
//
//
            }

        });

        Toast.makeText(MainActivity.this, "Firebase connection successful", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();

        knownParam.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                known = dataSnapshot.getValue(boolean.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        image.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

//                String link1 = dataSnapshot.getValue(String.class);
                if (known)
                    Picasso.get().load(link).into(imageView);
                else
                    Picasso.get().load(link2).into(imageView);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        someName.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String val2 = dataSnapshot.getValue(String.class);

                System.out.println(val2);
//                try {
////                    if (val2 == null || val2.isEmpty())
////                        imName.setText("Unknown Person");
////                    else
////                        imName.setText(val2);
//                } catch (Exception e) {
//                    imName.setText("Unknown Person");
//                }
                imName.setText(val2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }




}
