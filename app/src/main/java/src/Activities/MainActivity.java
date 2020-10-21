package src.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tp_cuatrimestral.R;

import src.Services.ContextManagerService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ContextManagerService.setContext(this.getBaseContext());
        setContentView(R.layout.activity_main);
    }

    public void onLogin(View view) {
        startActivity(new Intent(this, SystemActivity.class));
    }
}