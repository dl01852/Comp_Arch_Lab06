package com.example.CSCI_5331_Spring2016_Lab06_DavidLewis;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class MyActivity extends Activity implements AdapterView.OnItemClickListener {
    /**
     * Called when the activity is first created.
     */
    GridView myGrid;
    MyAdapter imageAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        myGrid = (GridView)findViewById(R.id.gridView);
        imageAdapter = new MyAdapter(this);
        myGrid.setAdapter(imageAdapter);
        myGrid.setOnItemClickListener(this);



    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        ViewHolder image = (ViewHolder)view.getTag();
        Country tempCountry = (Country) image.countryImage.getTag();
        String countryName = tempCountry.countryName;
        Toast.makeText(MyActivity.this, countryName + " flag was selected which is position " + position, Toast.LENGTH_SHORT).show();
    }
}

