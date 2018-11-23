package se.mdh.dva232.dva232currencyconverter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ConversionRates extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion_rates);

        /*
        // resource (array)
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.currencies_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);

        // listview
        ListView listview = findViewById(R.id.conversion_rates_list_all);
        listview.setAdapter(adapter);
        */

        String[] currencies = getResources().getStringArray(R.array.currencies_array);
        ListView mListView = (ListView)findViewById(R.id.conversion_rates_list_all);
        mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, currencies));

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent,View view, int pos, long id) {
                Log.d("TEST", "ListView click: " + parent.getItemAtPosition(pos).toString());

                // 1. Instantiate an <code><a href="/reference/android/app/AlertDialog.Builder.html">AlertDialog.Builder</a></code> with its constructor
                AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
                // 2. Chain together various setter methods to set the dialog characteristics
                String output = null;
                Log.d("SWITCH-INPUT", "pos: " + pos );
                switch( pos ) {
                    case 0: String[] currencyEUR = getResources().getStringArray(R.array.EUR);
                            output = getStringFromArray(currencyEUR);
                            break;
                    case 1: String[] currencySEK = getResources().getStringArray(R.array.SEK);
                            output = getStringFromArray(currencySEK);
                            break;
                    case 2: String[] currencyUSD = getResources().getStringArray(R.array.USD);
                            output = getStringFromArray(currencyUSD);
                            break;
                    case 3: String[] currencyGBP = getResources().getStringArray(R.array.GBP);
                            output = getStringFromArray(currencyGBP);
                        break;
                    case 4: String[] currencyCNY = getResources().getStringArray(R.array.CNY);
                            output = getStringFromArray(currencyCNY);
                        break;
                    case 5: String[] currencyJPY = getResources().getStringArray(R.array.JPY);
                            output = getStringFromArray(currencyJPY);
                        break;
                    case 6: String[] currencyKRW = getResources().getStringArray(R.array.KRW);
                            output = getStringFromArray(currencyKRW);
                        break;
                }

                builder.setMessage(output).setTitle(parent.getItemAtPosition(pos).toString() + " --> ???");
/*
                builder.setTitle(parent.getItemAtPosition(pos).toString() + " --> ???").setItems(R.array.EUR, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog_interface, int which) {
                                Log.d("DIALOG", "TEST");
                            }
                        });
*/
                        // 3. Get the <code><a href="/reference/android/app/AlertDialog.html">AlertDialog</a></code> from <code><a href="/reference/android/app/AlertDialog.Builder.html#create()">create()</a></code>
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }

    public String getStringFromArray(String[] array) {
        return "EUR: " + array[0] + "\nSEK: " + array[1] + "\nUSD: "+ array[2] + "\nGBP: "+ array[3] + "\nCNY: "+ array[4] + "\nJPY: "+ array[5] + "\nKRW: "+ array[6];
    }

    /*
    // Listview
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        Toast.makeText(this, "you selected " + parent.getItemAtPosition(pos).toString(), Toast.LENGTH_SHORT).show();

    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
    */


}
