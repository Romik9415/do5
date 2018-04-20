package com.src.graphapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.src.graphapp.R;
import com.src.graphapp.texts.TextsEN;

public class VertexActivity extends AppCompatActivity implements View.OnClickListener {

    Button bHelp, bInsert;
    EditText etVertex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertex);

        bHelp = (Button)findViewById(R.id.bHelp);
        bInsert = (Button)findViewById(R.id.bInsert);
        etVertex = (EditText)findViewById(R.id.etVertex);

//        bHelp.setOnClickListener(this);
        bInsert.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_vertex, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        String vertex = etVertex.getText().toString();

        switch (v.getId()){
            case R.id.bHelp:
                Toast.makeText(VertexActivity.this, TextsEN.getHelpByPosition(2), Toast.LENGTH_LONG).show();
                break;
            case R.id.bInsert:
                if (vertex.equals("")){
                    Toast.makeText(VertexActivity.this, TextsEN.getHelpByPosition(2), Toast.LENGTH_LONG).show();
                }else {
                    Intent i = new Intent(getApplicationContext(), MenuActivity.class);
                    i.putExtra("previous", 1);
                    i.putExtra("vertex", vertex);
                    startActivity(i);
                    finish();
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MenuActivity.class));
        finish();
    }
}
