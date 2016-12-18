package com.example.sathapornsunthornpan.onlinebikercommunity.blog;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sathapornsunthornpan.onlinebikercommunity.MainActivity;
import com.example.sathapornsunthornpan.onlinebikercommunity.R;
import com.example.sathapornsunthornpan.onlinebikercommunity.news.NewsWebViewActivity;


public class BlogCardFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private FloatingActionButton floatingActionButton;


    public BlogCardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlogCardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlogCardFragment newInstance(String param1, String param2) {
        // send
        BlogCardFragment fragment = new BlogCardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set title app
        getActivity().setTitle(R.string.menu_blog);


        // get
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blog, container, false);

        // fab
        floatingActionButton = ((MainActivity) getActivity()).getFloatingActionButton();
        floatingActionButton.show();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingActionButton.hide();

//                startActivity(new Intent(getActivity(), AddBlogActivity.class));
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_main, new InsertBlogFragment(), "NewFragmentTag");
                ft.commit();
                //

//                FragmentManager fragmentManager2 = getFragmentManager();
//                FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
//                DetailFragment fragment2 = new DetailFragment();
//                fragmentTransaction2.addToBackStack("xyz");
//                fragmentTransaction2.hide(MeinProfilFragment.this);
//                fragmentTransaction2.add(android.R.id.content, fragment2);
//                fragmentTransaction2.commit();
            }
        });
        // end fab

        return view;
    }


}
