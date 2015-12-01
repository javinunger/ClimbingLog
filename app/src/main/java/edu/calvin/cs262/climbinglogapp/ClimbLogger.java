package edu.calvin.cs262.climbinglogapp;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Chris on 11/2/2015.
 * This is the page that the user will see when the click on the big green button.
 */
public class ClimbLogger extends BaseActivity {

    EditText routeField, notesField;
    ExpandableListAdapter listAdapter;
    ExpandableListView listView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    String[] valueArray; //array to be sent to the database


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logger);

        //set the edittexts for later usage
        routeField  = (EditText)findViewById(R.id.routeNameField);
        notesField  = (EditText)findViewById(R.id.notesField);

        valueArray = new String[5]; //four values: type, route name, difficulty, color, and notes

        // get the listview
        listView = (ExpandableListView) findViewById(R.id.expandableListView);

        // preparing list data
        prepareListData();
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        // setting difficulty list adapter
        listView.setAdapter(listAdapter);

        // Listview on child click listener
        listView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                //If the route name is entered
                if (groupPosition == 0) {    //else if the type field is selected
                    //add value to array
                    valueArray[1] = parent.getExpandableListAdapter().getChild(groupPosition, childPosition).toString();
                    //display the selected value
                    Toast.makeText(getApplicationContext(), valueArray[1] + " added!", Toast.LENGTH_SHORT).show();
                    //collapse the group after a selection is made
                    listView.collapseGroup(groupPosition);
                } else if (groupPosition == 1) {    //else if the difficulty field is selected
                    //add value to array
                    valueArray[2] = parent.getExpandableListAdapter().getChild(groupPosition, childPosition).toString();
                    //display the selected value
                    Toast.makeText(getApplicationContext(), valueArray[2] + " added!", Toast.LENGTH_SHORT).show();
                    //collapse the group after a selection is made
                    listView.collapseGroup(groupPosition);
                } else if (groupPosition == 2) {    //else if the color field is selected
                    //add value to array
                    valueArray[3] = parent.getExpandableListAdapter().getChild(groupPosition, childPosition).toString();
                    //display the selected value
                    Toast.makeText(getApplicationContext(), valueArray[3] + " added!", Toast.LENGTH_SHORT).show();
                    //collapse the group after a selection is made
                    listView.collapseGroup(groupPosition);

                }
                return false;
            }
        });


        /**
         * These two methods check for presses outside of the keyboard, and if there are, hide the keyboard
         */
        routeField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
        notesField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

    }


    /*
     * Preparing the list data
     */

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding header data
        listDataHeader.add("Type");
        listDataHeader.add("Difficulty");
        listDataHeader.add("Color");

        // The types of climbs
        List<String> type = new ArrayList<String>();
        type.add("Top Rope");
        type.add("Boulder");

        // Adding difficulty data
        List<String> difficulty = new ArrayList<String>();
        difficulty.add("5.0");
        difficulty.add("5.1");
        difficulty.add("5.2");
        difficulty.add("5.3");
        difficulty.add("5.4");
        difficulty.add("5.5");
        difficulty.add("5.6");
        difficulty.add("5.7");
        difficulty.add("5.8");
        difficulty.add("5.9");
        difficulty.add("5.10a");
        difficulty.add("5.10b");
        difficulty.add("5.10c");
        difficulty.add("5.10d");
        difficulty.add("5.11a");
        difficulty.add("5.11b");
        difficulty.add("5.11c");
        difficulty.add("5.11d");
        difficulty.add("5.12a");
        difficulty.add("5.12b");
        difficulty.add("5.12c");
        difficulty.add("5.12d");
        difficulty.add("5.13a");
        difficulty.add("5.13b");
        difficulty.add("5.13c");
        difficulty.add("5.13d");
        difficulty.add("5.14a");
        difficulty.add("5.14b");
        difficulty.add("5.14c");
        difficulty.add("5.14d");
        difficulty.add("5.15a");
        difficulty.add("5.15b");


        //adding color data
        List<String> color = new ArrayList<String>();
        color.add("Red");
        color.add("Orange");
        color.add("Yellow");
        color.add("Green");
        color.add("Neon Green");
        color.add("Cyan");
        color.add("Blue");
        color.add("Turquoise");
        color.add("Violet");
        color.add("Pink");
        color.add("Magenta");
        color.add("Brown");
        color.add("White");
        color.add("Black");

        listDataChild.put(listDataHeader.get(0), type); // Header, Child data
        listDataChild.put(listDataHeader.get(1), difficulty); // Header, Child data
        listDataChild.put(listDataHeader.get(2), color); // Header, Child data

    }


    //this method handles the submit button and sends the app back to the home page
    public void submit(View view) {

        //if the edittexts have something other than the default value or empty, then add them to the array
        if(!routeField.getText().toString().equals("Route Name") && routeField.getText().toString().trim().length() != 0){
            valueArray[0] = routeField.getText().toString();
        }
        if(!notesField.getText().toString().equals("Notes") && notesField.getText().toString().trim().length() != 0){
            valueArray[4] = notesField.getText().toString();
        }

        //handle database stuff here
        //post valueArray

        //send the app back to the main activity
        Intent mainIntent = new Intent(ClimbLogger.this, MainActivity.class);
        ClimbLogger.this.startActivity(mainIntent);
        //some sort of submitted popup
        Toast.makeText(getApplicationContext(), valueArray[0] + valueArray[1]+ valueArray[2] + valueArray[3] + valueArray[4] + " added!", Toast.LENGTH_SHORT).show();
    }


    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * This method overrides the onOptionsItemSelected method in order to handle action bar things
     * specific to this page (eg the help dialog).
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_help:
                //simple dialog when the help setting is selected
                AlertDialog alertDialog = new AlertDialog.Builder(ClimbLogger.this).create();
                alertDialog.setTitle(getString(R.string.action_help));
                alertDialog.setMessage(getString(R.string.action_help_logger));
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}