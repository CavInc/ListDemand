package cav.listdemand.ui.fragments;

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


public class CloseDemandFragment extends Fragment {

    private RecyclerView mRecyclerView;

    private List<DemandModel> mDemandData;

    public CloseDemandFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static CloseDemandFragment newInstance() {
        CloseDemandFragment fragment = new CloseDemandFragment();
       // Bundle args = new Bundle();
      //  args.putString(ARG_PARAM1, param1);
       // args.putString(ARG_PARAM2, param2);
     //   fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_close_demand, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.list);

        loadData();

        return rootView;
    }

    private void loadData(){
        if (ScorocodeSdk.getSessionId()!=null){
            Log.d(ConstantManager.TAG_PREFIX,ScorocodeSdk.getSessionId());
        }

        Query query = new Query("demand");
        query.equalTo("close_demand",true);

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
