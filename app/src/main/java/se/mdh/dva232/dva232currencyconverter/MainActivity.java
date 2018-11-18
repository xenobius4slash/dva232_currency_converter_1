package se.mdh.dva232.dva232currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("LIFECYCLE", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//      Toast.makeText(this, "onCreate",Toast.LENGTH_SHORT).show();

        // resource (array)
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.currencies_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // left spinner
        Spinner spinner1 = (Spinner) findViewById(R.id.currencies_spinner1);
        spinner1.setAdapter(adapter);
        spinner1.setSelection(0);   // init value
        spinner1.setOnItemSelectedListener(this);

        // right spinner
        Spinner spinner2 = (Spinner) findViewById(R.id.currencies_spinner2);
        spinner2.setAdapter(adapter);
        spinner2.setSelection(1);   // init value
        spinner2.setOnItemSelectedListener(this);
    }

    @Override
    protected void onStart() {
        Log.d("LIFECYCLE", "onStart");
        super.onStart();
//        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        Log.d("LIFECYCLE", "onResume");
        super.onResume();
//        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        Log.d("LIFECYCLE", "onPause");
        super.onPause();
//       Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        Log.d("LIFECYCLE", "onStop");
        super.onStop();
//        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        Log.d("LIFECYCLE", "onDestroy");
        super.onDestroy();
//        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
    }

    // Spinner
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        if( parent.getId() == R.id.currencies_spinner1 ) {
            Toast.makeText(this, "(left) you selected " + parent.getItemAtPosition(pos).toString(), Toast.LENGTH_SHORT).show();
        } else if(parent.getId() == R.id.currencies_spinner2 ) {
            Toast.makeText(this, "(right) you selected " + parent.getItemAtPosition(pos).toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}
