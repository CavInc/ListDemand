package cav.listdemand.data.network;

import android.util.Log;

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
        Document document = new Document("demand");
        HashMap<String, Object> dm = document.getDocumentContent().getContent();

        Query query = new Query("demand");
        query.findDocuments(new CallbackFindDocument(){

            @Override
            public void onDocumentFound(List<DocumentInfo> documentInfos) {
                if (documentInfos != null){
                    for (int i=0;i<documentInfos.size();i++){
                        Log.d(ConstantManager.TAG_PREFIX, (String) documentInfos.get(i).get("title"));
                        Log.d(ConstantManager.TAG_PREFIX, (String) documentInfos.get(i).get("body_doc"));

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
        return mData;
    }
}
