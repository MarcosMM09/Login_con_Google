package com.example.logincongoogle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    CircleImageView img;
    TextView tv1,tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*if (AccessToken.getCurrentAccessToken() == null){
            goLoginScreen();
        }*/

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        img = findViewById(R.id.image1);
        tv1=findViewById(R.id.tv1);
        tv2=findViewById(R.id.tv2);
        if (user!=null){
            String name=user.getDisplayName();
            String gmail=user.getEmail();

            tv1.setText("Usuario  " + name  );
            tv2.setText(" Email " + gmail);
            Picasso.get().load(user.getPhotoUrl()).placeholder(R.drawable.loginn).into(img);
        }else {
            getApplicationContext();

        }
    }

        //metodo para ir al logueo
    private void goLoginScreen() {
        Intent intent = new Intent(this, login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void serra(View view){
        FirebaseAuth.getInstance().signOut();
        finish();

        //cerrar sesion con facebook
        LoginManager.getInstance().logOut();
        goLoginScreen();
    }
}