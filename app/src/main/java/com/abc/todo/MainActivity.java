package com.abc.todo;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;


    public static String KEY_ID = "id";
    public static String KEY_TASK = "task";
    public static String KEY_PRIORITY = "priority";
    public static String KEY_ISCOMPLETE = "isComplete";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_view:
                    //mTextMessage.setText(R.string.view);
                    setTitle("View Tasks");
                    ViewFragment viewFragment = new ViewFragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame, viewFragment);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_add:
                    //mTextMessage.setText(R.string.add);
                    setTitle("Add Tasks");
                    AddFragment addFragment = new AddFragment();
                    FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction2.replace(R.id.frame, addFragment);
                    fragmentTransaction2.commit();
                    return true;
                case R.id.navigation_history:
                    //mTextMessage.setText(R.string.history);
                    setTitle("History");
                    HistoryFragment historyFragment = new HistoryFragment();
                    FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction3.replace(R.id.frame, historyFragment);
                    fragmentTransaction3.commit();

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        setTitle("View Tasks");
        ViewFragment viewFragment = new ViewFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, viewFragment, "FragmentName");
        fragmentTransaction.commit();
    }

}
