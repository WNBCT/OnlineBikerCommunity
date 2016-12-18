package com.example.sathapornsunthornpan.onlinebikercommunity.blog;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.support.v7.widget.AppCompatCheckBox;
import android.widget.Toast;

import com.example.sathapornsunthornpan.onlinebikercommunity.MainActivity;
import com.example.sathapornsunthornpan.onlinebikercommunity.R;

import java.util.ArrayList;
import java.util.Map;


public class InsertBlogFragment extends Fragment {
    /*
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private FloatingActionButton floatingActionButton;
    private ArrayList<String> tags;
    private CheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6;
    private Button saveButton;

    public InsertBlogFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InsertBlogFragment.
     */
    // TODO: Rename and change types and number of parameters
   /* public static InsertBlogFragment newInstance(String param1, String param2) {
        InsertBlogFragment fragment = new InsertBlogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_insert_blog, container, false);

        // checkbox
        checkBox1 = (CheckBox) view.findViewById(R.id.checkBoxBlog1);
        checkBox2 = (CheckBox) view.findViewById(R.id.checkBoxBlog2);
        checkBox3 = (CheckBox) view.findViewById(R.id.checkBoxBlog3);
        checkBox4 = (CheckBox) view.findViewById(R.id.checkBoxBlog4);
        checkBox5 = (CheckBox) view.findViewById(R.id.checkBoxBlog5);
        checkBox6 = (CheckBox) view.findViewById(R.id.checkBoxBlog6);
        saveButton = (Button) view.findViewById(R.id.btnInsertBlog);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Hello", Toast.LENGTH_SHORT);
            }
        });

        return view;
    }


    public void onCheckboxClicked() {
        if(checkBox1.isChecked())
            tags.add("101");

        if(checkBox2.isChecked())
            tags.add("102");

        if(checkBox3.isChecked())
            tags.add("103");

        if(checkBox4.isChecked())
            tags.add("104");

        if(checkBox5.isChecked())
            tags.add("105");

        if(checkBox6.isChecked())
            tags.add("106");

    }*/

    private CheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6;
    private Button saveButton;
    FragmentActivity listener;

    // This event fires 1st, before creation of fragment or any views
    // The onAttach method is called when the Fragment instance is associated with an Activity.
    // This does not mean the Activity is fully initialized.
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity){
            this.listener = (FragmentActivity) context;
        }
    }

    // This event fires 2nd, before views are created for the fragment
    // The onCreate method is called when the Fragment instance is being created, or re-created.
    // Use onCreate for any standard setup that does not require the activity to be fully created
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setContentView(R.layout.fragment_insert_blog);
    }

    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blog, parent, false);
    }

    // This event is triggered soon after onCreateView().
    // onViewCreated() is only called if the view returned from onCreateView() is non-null.
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // checkbox
        checkBox1 = (CheckBox) view.findViewById(R.id.checkBoxBlog1);
        checkBox2 = (CheckBox) view.findViewById(R.id.checkBoxBlog2);
        checkBox3 = (CheckBox) view.findViewById(R.id.checkBoxBlog3);
        checkBox4 = (CheckBox) view.findViewById(R.id.checkBoxBlog4);
        checkBox5 = (CheckBox) view.findViewById(R.id.checkBoxBlog5);
        checkBox6 = (CheckBox) view.findViewById(R.id.checkBoxBlog6);
        saveButton = (Button) view.findViewById(R.id.btnInsertBlog);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Hello", Toast.LENGTH_SHORT);
            }
        });
    }

    // This method is called when the fragment is no longer connected to the Activity
    // Any references saved in onAttach should be nulled out here to prevent memory leaks.
    @Override
    public void onDetach() {
        super.onDetach();
        this.listener = null;
    }

    // This method is called after the parent Activity's onCreate() method has completed.
    // Accessing the view hierarchy of the parent activity must be done in the onActivityCreated.
    // At this point, it is safe to search for activity View objects by their ID, for example.
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
