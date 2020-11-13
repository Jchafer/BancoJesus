package practicas.simarro.bancojesus.principal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import practicas.simarro.bancojesus.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton botonAcceder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_main);

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