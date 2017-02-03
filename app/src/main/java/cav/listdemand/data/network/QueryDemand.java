package cav.listdemand.data.network;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cav.listdemand.data.storage.models.DemandModel;
import cav.listdemand.utils.ConstantManager;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackFindDocument;
import ru.profit_group.scorocode_sdk.ScorocodeSdk;
import ru.profit_group.scorocode_sdk.scorocode_objects.Document;
import ru.profit_group.scorocode_sdk.scorocode_objects.DocumentInfo;
import ru.profit_group.scorocode_sdk.scorocode_objects.Query;

/**
 * Created by Kotov Alexandr on 31.01.17.
 *
 */
public class QueryDemand {

    private List<DemandModel> mData = null;

    public QueryDemand(int mode,String userId){
        if (ScorocodeSdk.getSessionId()!=null){
            Log.d(ConstantManager.TAG_PREFIX,ScorocodeSdk.getSessionId());
        }


        Query query = new Query("demand");
        query.findDocuments(new CallbackFindDocument(){

            @Override
            public void onDocumentFound(List<DocumentInfo> documentInfos) {
                if (documentInfos != null){
                    mData = new ArrayList<DemandModel>();

                    for (int i=0;i<documentInfos.size();i++){
                        Log.d(ConstantManager.TAG_PREFIX,(String) documentInfos.get(i).getId());
                        Log.d(ConstantManager.TAG_PREFIX, (String) documentInfos.get(i).get("title"));
                        Log.d(ConstantManager.TAG_PREFIX, (String) documentInfos.get(i).get("body_doc"));

                        String operator_id = (String) documentInfos.get(i).get("operator_id")!=null ? (String) documentInfos.get(i).get("operator_id") : "";

                        mData.add(new DemandModel((String) documentInfos.get(i).getId(),
                                operator_id,
                                (String) documentInfos.get(i).get("title"),
                                (String) documentInfos.get(i).get("body_doc"),
                                (Boolean) documentInfos.get(i).get("close_demand")));

                    }

                }
            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                Log.d(ConstantManager.TAG_PREFIX,errorCode+" "+errorMessage);
            }
        });


    }

    public List<DemandModel> getData() {
      //  Log.d(ConstantManager.TAG_PREFIX, String.valueOf(mData.size()));
        return mData;
    }
}
