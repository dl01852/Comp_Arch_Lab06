package com.example.CSCI_5331_Spring2016_Lab06_DavidLewis;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by David on 2/19/2016.
 */

 class MyAdapter extends BaseAdapter {

    private Context context;
    ArrayList<Country> countries;
    public MyAdapter(Context context)
    {
        this.context = context;
        countries = new ArrayList<>();
        Resources resources = context.getResources(); // this is needed to grab the country names in strings.xml
        String[] tempCountryName = resources.getStringArray(R.array.country_names); // country_names is what i named the array in the strings.xml
        int[] countryImages = {R.drawable.germany,R.drawable.argentina,R.drawable.netherlands,R.drawable.brazil, R.drawable.france,
                               R.drawable.mexico, R.drawable.belgium,R.drawable.italy, R.drawable.russia};
        for(int i = 0; i < countryImages.length; i++)
        {
            // The way the countries are laid out in the image array have to match up with the naming in the strings.xml
            Country tempCountry = new Country(countryImages[i],tempCountryName[i]);
            countries.add(tempCountry);
        }

    }
    @Override
    public int getCount() {
        return countries.size();
    }

    @Override
    public Object getItem(int position) {
        return countries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    /* Note: Explaining getView.
       getView is called by the gridView each time it needs to display an item within a specific space on the grid.
       This method is going to have it call to the imageView we created in the single_image.xml
       and for each spot on the grid view, We are going change the image source of that imageView.
       In Essence, One ImageView is being used for ALL spots within the gridView. We are creating instances of it
       just with a different source(a new flag)
       position
     */
    public View getView(int position, View convertView, ViewGroup gridView) { // ViewGroup is always the view that'll need to use this adapter

        View layout = convertView; // layout is going to refer to the layout in single_image.xml(which is a relative layout)
        ViewHolder imageHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); // this will give us a new object from single_image.xml
            layout = inflater.inflate(R.layout.single_image, gridView, false); // first param, is referring to the xml file i created in the layout folder.
            imageHolder = new ViewHolder(layout);
            layout.setTag(imageHolder); // this will hold the object of imageHolder to be recycled when i create a new spt on the grid.(optimization trick)
        }
        // recycling now since we aren't creating an image for the first time.
        else
        {
            imageHolder = (ViewHolder) layout.getTag();


        }

        imageHolder.countryImage.setImageResource(countries.get(position).imageId);
        imageHolder.countryImage.setTag(countries.get(position));
        return layout;

    }
}


// class to hold a reference for each image to optimize the app.
class ViewHolder
{
    ImageView countryImage;
    ViewHolder(View v) // this value will be passed to us when we use the layout inflator.
    {
        countryImage = (ImageView)v.findViewById(R.id.imageView); // We only want to peform this once cause it's an expensive operation.
    }

    public void setImage(ImageView image)
    {
         countryImage = image;
    }
}

// Class to contain data about the flag or country
class Country
{
    int imageId;
    String countryName;

    public Country(int id, String name)
    {
        imageId = id;
        countryName = name;
    }
}
