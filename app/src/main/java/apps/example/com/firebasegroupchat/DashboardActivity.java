package apps.example.com.firebasegroupchat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import apps.example.com.firebasegroupchat.adapter.MessageAdapter;
import apps.example.com.firebasegroupchat.models.Message;
import apps.example.com.firebasegroupchat.models.MessageData;
import apps.example.com.firebasegroupchat.models.User;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener, ValueEventListener {

    private static final String TAG = "DashboardActivity";
    FirebaseAuth auth;
    FirebaseDatabase database;
    User u;

    RecyclerView rvMessage;
    EditText etMessage;
    ImageButton btnSend;
    MessageData messages;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        messages=new MessageData();

        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        u=new User();

        rvMessage=findViewById(R.id.rvMessage);
        etMessage=findViewById(R.id.etMessage);
        btnSend=findViewById(R.id.btnSend);
        btnSend.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        final FirebaseUser currentUser=auth.getCurrentUser();
        u.setUid(currentUser.getUid());
        u.setEmail(currentUser.getEmail());
        database.getReference("user").child(currentUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                u=dataSnapshot.getValue(User.class);
                u.setUid(currentUser.getUid());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        database.getReference("messages").addValueEventListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menuLogout){
            auth.signOut();
            startActivity(new Intent(DashboardActivity.this,MainActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if(!TextUtils.isEmpty(etMessage.getText().toString())) {
            Message message = new Message(etMessage.getText().toString(), u.getName());
            messages.addMessage(message);
            etMessage.setText("");
            database.getReference("messages").setValue(messages);
        }
        else {
            Toast.makeText(this, "Please write message.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        MessageData messages=dataSnapshot.getValue(MessageData.class);
        if(null!=messages){
            this.messages=messages;
            rvMessage.setLayoutManager(new LinearLayoutManager(this));
            rvMessage.setAdapter(new MessageAdapter(this,this.messages));
        }
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
