package practicas.simarro.bancojesus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton botonAcceder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonAcceder = (ImageButton) findViewById(R.id.btAcceder);

        botonAcceder.setOnClickListener(this);

        /*Animation hyperspaceJump = AnimationUtils.loadAnimation(this, R.anim.hyperspace_jump);
        botonAcceder.startAnimation(hyperspaceJump);*/
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), AccesoActivity.class);
        startActivity(intent);
    }
}