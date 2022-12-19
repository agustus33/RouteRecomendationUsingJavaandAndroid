package com.android.routerecomendation.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.RouteRecomendation.R;
import database.LocationEntity;
import database.RouteDatabase;
import database.RoutesEntity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static Context mContext;
    ArrayList<String> priorityDisplay = new ArrayList<>() ;
    List<String> locationList = new ArrayList<>();
    ArrayAdapter<String > adapter1,adapter2,adapter3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getApplicationContext();
        setContentView(R.layout.activity_main);
        EditText editText1 = findViewById(R.id.searchEdit1);
        EditText editText2 = findViewById(R.id.searchEdit2);
        ListView listView1 = (ListView) findViewById(R.id.lv1);
        ListView listView2 = (ListView) findViewById(R.id.lv2);
        ListView listView3 = (ListView) findViewById(R.id.lv3);
        listView3.setVisibility(View.GONE);

        RouteDatabase db =Room.databaseBuilder(mContext,RouteDatabase.class,"ROUTES_DATA")
                .allowMainThreadQueries()
                .build();

        if(db.routesDao().getAllRoutes().size()<=0) {
            insertRoutesNames(db);
        }
        setLocationList();
        adapter1 = new ArrayAdapter<>(this, R.layout.list_item, R.id.product_name, locationList);
        adapter2 = new ArrayAdapter<>(this, R.layout.list_item, R.id.product_name, locationList);
        adapter3 = new ArrayAdapter<>(this, R.layout.list_item, R.id.product_name, priorityDisplay);
        listView1.setAdapter(adapter1);
        listView2.setAdapter(adapter2);

        editText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView1.setVisibility(View.VISIBLE);
            }
        });
        editText1.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                adapter1.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                Toast.makeText(getApplicationContext(),"before text change",Toast.LENGTH_LONG).show();
            }

            @Override
            public void afterTextChanged(Editable arg0) {
                Toast.makeText(getApplicationContext(),"after text change",Toast.LENGTH_LONG).show();
            }
        });

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = listView1.getItemAtPosition(position);
                editText1.setText(o.toString());
                listView1.setVisibility(View.GONE);
                System.out.println(o);
            }
        });
        editText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView2.setVisibility(View.VISIBLE);
            }
        });
        editText2.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                adapter2.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                Toast.makeText(getApplicationContext(),"before text change",Toast.LENGTH_LONG).show();
            }

            @Override
            public void afterTextChanged(Editable arg0) {
                Toast.makeText(getApplicationContext(),"after text change",Toast.LENGTH_LONG).show();
            }
        });

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = listView2.getItemAtPosition(position);
                editText2.setText(o.toString());
                listView2.setVisibility(View.GONE);
                System.out.println(o);

            }
        });

        Button findRoutesButton = findViewById(R.id.button_find);
        findRoutesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findRoutes(editText1.getText().toString(),editText2.getText().toString(),db);
                displayRoutes(db);

            }
        });

    }

    private void setLocationList()
    {
        locationList.add("location1");
        locationList.add("location2");
        locationList.add("location3");
        locationList.add("location4");
        locationList.add("location5");
        locationList.add("location6");
        locationList.add("location7");
        locationList.add("location8");
        locationList.add("location9");
        locationList.add("location10");
        locationList.add("location11");
        locationList.add("location12");
        locationList.add("location13");
    }

    private void insertRoutesNames(RouteDatabase db)
    {
        ArrayList<String> routesList = new ArrayList<>();
        routesList.add("location1→location2→location3→location4→location5→location6→location7→location8→location9→location10→location11→location12→location13");
        routesList.add("location1→location2→location3→location4→location5→location6→location7→location8→location9");
        routesList.add("location1→location2→location3→location4→location5→location6→location7→location8→location9→location10→location11");
        routesList.add("location1→location2→location3→location4");
        routesList.add("location1→location2→location3→location4→location5→location6→location7");

        for(int i =0; i<routesList.size();i++)
        {
            RoutesEntity r = new RoutesEntity(routesList.get(i),"null");
            db.routesDao().insertRoutes(r);
        }
    }
    private void findRoutes(String start, String end, RouteDatabase db) {
        List<String> temp = new ArrayList();
        List<String> displayList = new ArrayList();
        temp.addAll(db.routesDao().getAllRoutes());
        for(int i=0;i<temp.size();i++)
        {
            if(temp.get(i).contains(start) && temp.get(i).contains(end) )
            {
                String[] arrOfStr = temp.get(i).split("→",temp.size());
                Boolean startCrossed = false ;
                int gap = 0 ;
                for(int j=0;j<arrOfStr.length;j++)
                {
                    if (arrOfStr[j].equals(start) || startCrossed==true)
                    {
                        startCrossed = true;
                        gap = gap+1;
                        if (arrOfStr[j].equals(end))
                        {
                            int finalGap  =gap;
                            LocationEntity loc = new LocationEntity(finalGap,temp.get(i));
                            db.locationDao().insertLocations(loc);
                        }
                    }
                }
            }
        }
    }

    private void displayRoutes(RouteDatabase db)
    {
        List<LocationEntity> all = db.locationDao().orderedRoutes();
        priorityDisplay.add(all.get(0).updatedList);
        priorityDisplay.add(all.get(1).updatedList);
        priorityDisplay.add(all.get(2).updatedList);
        ListView listView3 = (ListView) findViewById(R.id.lv3);
        listView3.setVisibility(View.VISIBLE);
        listView3.setAdapter(adapter3);



    }
}

