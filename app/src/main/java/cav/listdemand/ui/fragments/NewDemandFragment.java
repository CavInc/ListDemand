package cav.listdemand.ui.fragments;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cav.listdemand.R;
import cav.listdemand.data.network.QueryDemand;
import cav.listdemand.data.storage.models.DemandModel;
import cav.listdemand.utils.ConstantManager;


public class NewDemandFragment extends Fragment {


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView mRecyclerView;

    private List<DemandModel> mDemandData;

    public NewDemandFragment() {

    }


    // TODO: Rename and change types and number of parameters
    public static NewDemandFragment newInstance() {
        NewDemandFragment fragment = new NewDemandFragment();
       // Bundle args = new Bundle();
      //  args.putString(ARG_PARAM1, param1);
       // args.putString(ARG_PARAM2, param2);
       // fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_new_demand, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.list);

        //TODO потом переделать что бы не тягать кажжый раз из облака
        // получаем данные из облака
        mDemandData = new QueryDemand(ConstantManager.GET_NEW_QUERY,"").getData();

        return rootView;
    }


}
