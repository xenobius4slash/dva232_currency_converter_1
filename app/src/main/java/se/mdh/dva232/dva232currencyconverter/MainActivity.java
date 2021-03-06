package se.mdh.dva232.dva232currencyconverter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public Integer integerCurrency1 = null;
    public Integer integerCurrency2 = null;
    public Boolean savedInstance = false;
    public String SAVED_ET1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("LIFECYCLE", "onCreate");
        Log.d("SAVE", "savedInstanceState: " + savedInstanceState);
        if( savedInstanceState != null ) {
            Log.d("SAVE", "savedInstanceState != null");
            savedInstance = true;
        }
        setContentView(R.layout.activity_main);

        /*
         * Menu icon workaround
         */
        try {   ViewConfiguration config = ViewConfiguration.get(this);
                Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
                if (menuKeyField != null) {
                    menuKeyField.setAccessible(true);
                    menuKeyField.setBoolean(config, false);
                }
        } catch (Exception ignored) { }


        /*
         * resource (array)
         */
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.currencies_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        /*
         * top spinner
         */
        Spinner spinner1 = findViewById(R.id.currencies_spinner1);
        spinner1.setAdapter(adapter);

        if(savedInstanceState!=null && savedInstanceState.containsKey("SP1")) {
            Log.d("SAVE","key 'SP1' exist");
            spinner1.setSelection(savedInstanceState.getInt("SP1"));    // saved value
        } else {
            Log.d("SAVE","key 'SP1' NOT exist");
            spinner1.setSelection(0);   // init value
        }
        spinner1.setOnItemSelectedListener(this);

        /*
         * top editText
         */
        final EditText editText1 = findViewById(R.id.input_value);
        if(savedInstanceState!=null && savedInstanceState.containsKey("ET1")) {
            Log.d("SAVE","key 'ET1' exist");
            SAVED_ET1 = savedInstanceState.getString("ET1");
        }
        editText1.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if( editText1.hasFocus() && !savedInstance ) {
                    String resultText;
                    resultText = getTextForEditText(s.toString());
                    TextView textView = findViewById(R.id.result);
                    textView.setText(resultText);
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
        });

        /*
         * bottom spinner
         */
        Spinner spinner2 = findViewById(R.id.currencies_spinner2);
        spinner2.setAdapter(adapter);
        if(savedInstanceState!=null && savedInstanceState.containsKey("SP2")) {
            Log.d("SAVE","key 'SP2' exist");
            spinner2.setSelection(savedInstanceState.getInt("SP2"));    // saved value
        } else {
            spinner2.setSelection(1);   // init value
        }
        spinner2.setOnItemSelectedListener(this);

        TextView textView = findViewById(R.id.result);
        Log.d("DEBUG","textView: " + textView.getText().toString());
        textView.setText(R.string.hint_input_value);

        /*
         * button
         */
        final Button button = findViewById(R.id.button_to_conversion_rates);
        Log.d("VISIBILITY", "button: " + button);
        if( button != null) {
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    openConversionRates();
                }
            });
        }

    }

    @Override
    protected void onStart() {
        Log.d("LIFECYCLE", "onStart");
        super.onStart();
        Spinner spinner1 = findViewById(R.id.currencies_spinner1);
        Spinner spinner2 = findViewById(R.id.currencies_spinner2);
        EditText editText1 = findViewById(R.id.input_value);
        TextView textView = findViewById(R.id.result);
//        EditText editText2 = findViewById(R.id.input_value2);
        Log.d("DEBUG", "onStart() => SP1: "+spinner1.getSelectedItemPosition()+" // ET1: "+editText1.getText().toString()+" ("+SAVED_ET1+") // SP2: "+spinner2.getSelectedItemPosition()+" // TV: "+textView.getText().toString());
    }

    @Override
    protected void onResume() {
        Log.d("LIFECYCLE", "onResume");
        super.onResume();
        Spinner spinner1 = findViewById(R.id.currencies_spinner1);
        Spinner spinner2 = findViewById(R.id.currencies_spinner2);
        EditText editText1 = findViewById(R.id.input_value);
        TextView textView = findViewById(R.id.result);
//        EditText editText2 = findViewById(R.id.input_value2);
        Log.d("DEBUG", "onResume() => SP1: "+spinner1.getSelectedItemPosition()+" // ET1: "+editText1.getText().toString()+" ("+SAVED_ET1+") // SP2: "+spinner2.getSelectedItemPosition()+" // TV: "+textView.getText().toString());
    }

    @Override
    protected void onPause() {
        Log.d("LIFECYCLE", "onPause");
        super.onPause();
        Spinner spinner1 = findViewById(R.id.currencies_spinner1);
        Spinner spinner2 = findViewById(R.id.currencies_spinner2);
        EditText editText1 = findViewById(R.id.input_value);
        TextView textView = findViewById(R.id.result);
//        EditText editText2 = findViewById(R.id.input_value2);
        Log.d("DEBUG", "onPause() => SP1: "+spinner1.getSelectedItemPosition()+" // ET1: "+editText1.getText().toString()+" ("+SAVED_ET1+") // SP2: "+spinner2.getSelectedItemPosition()+" // TV: "+textView.getText().toString());
    }

    @Override
    protected void onStop() {
        Log.d("LIFECYCLE", "onStop");
        super.onStop();
        Spinner spinner1 = findViewById(R.id.currencies_spinner1);
        Spinner spinner2 = findViewById(R.id.currencies_spinner2);
        EditText editText1 = findViewById(R.id.input_value);
        TextView textView = findViewById(R.id.result);
//        EditText editText2 = findViewById(R.id.input_value2);
        Log.d("DEBUG", "onStop() => SP1: "+spinner1.getSelectedItemPosition()+" // ET1: "+editText1.getText().toString()+" ("+SAVED_ET1+") // SP2: "+spinner2.getSelectedItemPosition()+" // TV: "+textView.getText().toString());
    }

    @Override
    protected void onDestroy() {
        Log.d("LIFECYCLE", "onDestroy");
        super.onDestroy();
        Spinner spinner1 = findViewById(R.id.currencies_spinner1);
        Spinner spinner2 = findViewById(R.id.currencies_spinner2);
        EditText editText1 = findViewById(R.id.input_value);
        TextView textView = findViewById(R.id.result);
//        EditText editText2 = findViewById(R.id.input_value2);
        Log.d("DEBUG", "onDestroy() => SP1: "+spinner1.getSelectedItemPosition()+" // ET1: "+editText1.getText().toString()+" ("+SAVED_ET1+") // SP2: "+spinner2.getSelectedItemPosition()+" // TV: "+textView.getText().toString());
    }

    /*
     * SaveInstanceState
     */
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        Log.d("SAVE","onSaveInstanceState");
        Spinner spinner1 = findViewById(R.id.currencies_spinner1);
        savedInstanceState.putInt("SP1", spinner1.getSelectedItemPosition() );
        EditText editText1 = findViewById(R.id.input_value);
        savedInstanceState.putString("ET1", editText1.getText().toString());
        Spinner spinner2 = findViewById(R.id.currencies_spinner2);
        savedInstanceState.putInt("SP2", spinner2.getSelectedItemPosition() );
//        Log.d("SAVE", "SP1: " + savedInstanceState.getInt("SP1") + " // ET1: " + savedInstanceState.getString("ET1") + " // SP2: " + savedInstanceState.getInt("SP2") + " // ET2: " + savedInstanceState.getString("ET2"));
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("SAVE","onRestoreInstanceState");
//        Log.d("SAVE", "onRestoreInstanceState(..) => SAVED_ET1: "+SAVED_ET1+"// SAVED_ET2; " + SAVED_ET2);
        EditText editText1 = findViewById(R.id.input_value);
        editText1.setText( SAVED_ET1 );
//        EditText editText2 = findViewById(R.id.input_value2);
//        editText2.setText( SAVED_ET2 );
    }

    /*
     * Spinner
     */
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        if( parent.getId() == R.id.currencies_spinner1 ) {
            integerCurrency1 = pos;
        } else if(parent.getId() == R.id.currencies_spinner2 ) {
            integerCurrency2 = pos;
        }
        EditText editText1 = findViewById(R.id.input_value);
        String input = editText1.getText().toString();
        TextView textView = findViewById(R.id.result);
        textView.setText( getTextForEditText(input) );
    }
    public void onNothingSelected(AdapterView<?> parent) { }

    /*
     * Menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.conversion_rates:
                openConversionRates();
                return true;
            case R.id.about:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.menu_about_content).setTitle(R.string.menu_about);
                AlertDialog dialog = builder.create();
                dialog.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Open the activity for the conversion rates list
     */
    public void openConversionRates() {
        Intent intent = new Intent(this, ConversionRates.class);
        this.startActivity(intent);
    }

    /**
     * Returns the conversion rate for a source (from) and target (to) currency
     *
     * @param currencyFrom      source currency
     * @param currencyTo        target currency
     * @return                  conversion rate
     */
    public Double getCurrencyRate(int currencyFrom, int currencyTo) {
        String conversionRate;
        switch( currencyFrom ) {
            case 0: String[] currencyEUR = getResources().getStringArray(R.array.EUR);
                conversionRate = currencyEUR[currencyTo];
                break;
            case 1: String[] currencySEK = getResources().getStringArray(R.array.SEK);
                conversionRate = currencySEK[currencyTo];
                break;
            case 2: String[] currencyUSD = getResources().getStringArray(R.array.USD);
                conversionRate = currencyUSD[currencyTo];
                break;
            case 3: String[] currencyGBP = getResources().getStringArray(R.array.GBP);
                conversionRate = currencyGBP[currencyTo];
                break;
            case 4: String[] currencyCNY = getResources().getStringArray(R.array.CNY);
                conversionRate = currencyCNY[currencyTo];
                break;
            case 5: String[] currencyJPY = getResources().getStringArray(R.array.JPY);
                conversionRate = currencyJPY[currencyTo];
                break;
            case 6: String[] currencyKRW = getResources().getStringArray(R.array.KRW);
                conversionRate = currencyKRW[currencyTo];
                break;
            default: conversionRate = "0";
        }
        return Double.parseDouble(conversionRate);
    }

    /**
     *
     * @param d     Double for round (#.##)
     * @return      Double
     */
    double roundTwoDecimals(double d)
    {
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
        otherSymbols.setDecimalSeparator('.');
        DecimalFormat twoDForm = new DecimalFormat("#.##", otherSymbols);
//        Log.d("decimalnumber","roundTwoDecimals #1: " + twoDForm.format(d));
//        Log.d("decimalnumber","roundTwoDecimals #2: " + Double.valueOf(twoDForm.format(d)));
        return Double.valueOf(twoDForm.format(d));
    }

    /**
     * Returns the text for the target TextView field.
     *
     * @param input         String included the text of the EditText
     * @return              String
     */
    public String getTextForEditText(String input) {
        Double from;
        Double result;
        Double currencyRate = null;
        String resultText;

        if (input.length() > 0 && integerCurrency1 != null && integerCurrency2 != null) {
            from = Double.parseDouble(input);
            Log.d("DEBUG","intCurr1: "+integerCurrency1+" // intCurr2: " + integerCurrency2);
            currencyRate = getCurrencyRate(integerCurrency1, integerCurrency2);
            if (currencyRate == 0.0) {
                result = roundTwoDecimals(from);
            } else {
                result = roundTwoDecimals((from * currencyRate));
            }
            resultText = String.valueOf(result);
        } else {
            resultText = "";
        }
        return resultText;
    }
}
