package cav.listdemand.ui.fragments;

import android.net.ConnectivityManager;
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
import cav.listdemand.data.network.QueryDemand;
import cav.listdemand.data.storage.models.DemandModel;
import cav.listdemand.ui.adapters.DemandAdapter;
import cav.listdemand.utils.ConstantManager;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackFindDocument;
import ru.profit_group.scorocode_sdk.ScorocodeSdk;
import ru.profit_group.scorocode_sdk.scorocode_objects.DocumentInfo;
import ru.profit_group.scorocode_sdk.scorocode_objects.Query;


public class NewDemandFragment extends Fragment {


    private static final String TAG = "NEW_DEMAND";
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
        //mDemandData = new QueryDemand(ConstantManager.GET_NEW_QUERY,"").getData();
        //Log.d(TAG, String.valueOf(mDemandData.size()));
      //  DemandAdapter adapter = new DemandAdapter(mDemandData);

        //mRecyclerView.setAdapter(adapter);
        loadData();
        return rootView;
    }

    private void loadData(){
        if (ScorocodeSdk.getSessionId()!=null){
            Log.d(ConstantManager.TAG_PREFIX,ScorocodeSdk.getSessionId());
        }

        Query query = new Query("demand");
        query.equalTo("close_demand",false);
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
                    DemandAdapter adapter = new DemandAdapter(mDemandData,mItemListener);
                    mRecyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                Log.d(ConstantManager.TAG_PREFIX,errorCode+" "+errorMessage);
            }
        });

    }

    private DemandAdapter.CustomClickListener mItemListener = new DemandAdapter.CustomClickListener() {
        @Override
        public void onUserItemClickListener(int adapterPosition) {
            Log.d(ConstantManager.TAG_PREFIX,"Items position "+adapterPosition);
        }
    };


}
