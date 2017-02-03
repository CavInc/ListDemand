package cav.listdemand.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cav.listdemand.R;
import cav.listdemand.data.storage.models.DemandModel;
import cav.listdemand.ui.adapters.DemandAdapter;
import cav.listdemand.utils.ConstantManager;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackFindDocument;
import ru.profit_group.scorocode_sdk.ScorocodeSdk;
import ru.profit_group.scorocode_sdk.scorocode_objects.DocumentInfo;
import ru.profit_group.scorocode_sdk.scorocode_objects.Query;

public class WorkDemandFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mUserID;
    private String mParam2;

    private RecyclerView mRecyclerView;

    private List<DemandModel> mDemandData;


    public WorkDemandFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static WorkDemandFragment newInstance(String userID) {
        WorkDemandFragment fragment = new WorkDemandFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, userID);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUserID = getArguments().getString(ARG_PARAM1);
           // mParam2 = getArguments().getString(ARG_PARAM2);
            Log.d(ConstantManager.TAG_PREFIX+" WORK ",mUserID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_work_demand, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.list);
        loadData();
        return rootView;
    }

    private void loadData(){
        if (ScorocodeSdk.getSessionId()!=null){
            Log.d(ConstantManager.TAG_PREFIX,ScorocodeSdk.getSessionId());
        }

        Query query = new Query("demand");
        query.equalTo("close_demand",false).equalTo("operator_id",mUserID);
        query.findDocuments(new CallbackFindDocument(){

            @Override
            public void onDocumentFound(List<DocumentInfo> documentInfos) {
                if (documentInfos != null){
                    mDemandData = new ArrayList<DemandModel>();

                    for (int i=0;i<documentInfos.size();i++){
                        Log.d(ConstantManager.TAG_PREFIX,(String) documentInfos.get(i).getId());
                        Log.d(ConstantManager.TAG_PREFIX, (String) documentInfos.get(i).get("title"));
                        Log.d(ConstantManager.TAG_PREFIX, (String) documentInfos.get(i).get("body_doc"));

                        String operator_id = (String) documentInfos.get(i).get("operator_id")!=null ? (String) documentInfos.get(i).get("operator_id") : "";

                        mDemandData.add(new DemandModel((String) documentInfos.get(i).getId(),
                                operator_id,
                                (String) documentInfos.get(i).get("title"),
                                (String) documentInfos.get(i).get("body_doc"),
                                (Boolean) documentInfos.get(i).get("close_demand")));

                    }
                    DemandAdapter adapter = new DemandAdapter(mDemandData);
                    mRecyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                Log.d(ConstantManager.TAG_PREFIX,errorCode+" "+errorMessage);
            }
        });

    }



}
