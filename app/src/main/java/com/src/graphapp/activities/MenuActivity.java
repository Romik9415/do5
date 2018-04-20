package com.src.graphapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.src.graphapp.Controller;
import com.src.graphapp.R;
import com.src.graphapp.texts.TextsEN;

public class MenuActivity extends Activity implements View.OnClickListener {

    ListView lvMenu;
    Button bHelp;

    int controllerLog, previous;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent i = getIntent();
        previous = i.getIntExtra("previous", -1);
        if ((previous==0)||(previous==1)||(previous==2)) {
            controllerLog = Controller.main(i);
            switch (controllerLog) {
                case 1:
                    Toast.makeText(this, TextsEN.getErrorByPosition(0), Toast.LENGTH_LONG).show();
                    break;
                case 2:
                    Toast.makeText(this, TextsEN.getInsertionByPosition(0), Toast.LENGTH_LONG).show();
                    break;
                case 3:
                    Toast.makeText(this, TextsEN.getErrorByPosition(1), Toast.LENGTH_LONG).show();
                    break;
                case 4:
                    Toast.makeText(this, TextsEN.getErrorByPosition(2), Toast.LENGTH_LONG).show();
                    break;
                case 5:
                    Toast.makeText(this, TextsEN.getInsertionByPosition(1), Toast.LENGTH_LONG).show();
                    break;
                case 6:
                    Toast.makeText(this, TextsEN.getErrorByPosition(6), Toast.LENGTH_LONG).show();
                    break;
            }
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        lvMenu = (ListView) findViewById(R.id.lvMenu);
        bHelp = (Button)findViewById(R.id.bHelp);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, TextsEN.getMenu());

        lvMenu.setAdapter(adapter);

        lvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Controller.getGraph().cleanVisitedVertex();
                Controller.getGraph().cleanVisitedEdge();

                switch (position) {
                    case 0: //OK
                        startActivity(new Intent(getApplicationContext(), VertexActivity.class));
                        finish();
                        break;
                    case 1: //OK
                        startActivity(new Intent(getApplicationContext(), EdgeActivity.class));
                        finish();
                        break;
                    case 2: //OK
                        Intent i2 = new Intent(getApplicationContext(), GraphActivity.class);
                        i2.putExtra("title", TextsEN.getMenuByPosition(2));
                        i2.putExtra("algorithm", 2);
                        i2.putExtra("description", "");
                        i2.putExtra("complexity", "");
                        startActivity(i2);
                        break;
                    case 3:
                        Intent i4 = new Intent(getApplicationContext(), InputActivity.class);
                        i4.putExtra("algorithm", 4);
                        i4.putExtra("title", TextsEN.getMenuByPosition(3));
                        i4.putExtra("description", TextsEN.getDescriptionByPosition(1));
                        i4.putExtra("complexity", TextsEN.getComplexityByPosition(1));
                        startActivity(i4);
                        break;
                    case 4: //Floud WarshallActivity
                        Intent i9 = new Intent(getApplicationContext(), WarshallActivity.class);
                        i9.putExtra("title", TextsEN.getMenuByPosition(4));
                        i9.putExtra("description", TextsEN.getDescriptionByPosition(6));
                        i9.putExtra("complexity", TextsEN.getComplexityByPosition(6));
                        startActivity(i9);
                        break;
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
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
        switch (v.getId()){
            case R.id.bHelp:
                Toast.makeText(MenuActivity.this, TextsEN.getHelpByPosition(1), Toast.LENGTH_LONG).show();
                break;
        }
    }

    //Double Back to Exit Operation-----------------
    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Controller.destroy();
            System.exit(0);
            finish();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
    //----------------------------------------------
}
