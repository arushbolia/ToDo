package com.abc.todo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends Fragment {


    //String task;
        //int priority;
        DBHelper dbHelper;
        private EditText editTask, editPriority;
        private Button btnAdd;
        //private TextView tv;


        public AddFragment() {
            // Required empty public constructor
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View v =  inflater.inflate(R.layout.fragment_add, container, false);

            editTask = (EditText) v.findViewById(R.id.etTask);
            editPriority = (EditText) v.findViewById(R.id.etPriority);
            btnAdd = (Button) v.findViewById(R.id.btnSubmit);
            dbHelper = new DBHelper(getContext());
            //tv = (TextView) v.findViewById(R.id.textView);

            btnAdd.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                     String task = editTask.getText().toString();
                     String p = editPriority.getText().toString();
                     int priority = Integer.parseInt(p);
                     if(task.length() != 0 & priority != 0)
                     {
                         addData(task, priority);
                         editTask.setText("");
                         editPriority.setText("");
                     }
                     else if(task.length() != 0 && priority == 0)
                         {
                         toastMessage("Priority cannot be 0");
                     }
                     else if(task.length() == 0 && priority != 0)
                     {
                         toastMessage("Enter task");
                     }
                     else {
                         toastMessage("Please enter data in the fields");
                     }
                     //String text = "Task: " + task + " Priority: " + priority;
                     //tv.setText(text);
                }
            });

        return  v;
    }

    public void addData(String task, int prior)
    {
        boolean insertData = dbHelper.insert(task, prior);

        if(insertData)
            toastMessage("Data Successfully stored");
        else
            toastMessage("Something went wrong");
    }

    private void toastMessage(String message)
    {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

}
