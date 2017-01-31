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
    private static final String APPLICATION_ID = "9e1c8b8022964587af2f596b0228978e";
    private static final java.lang.String CLIENT_KEY = "1e3c8c0846c54a909d8cda5ea28002b4";
    private static final String FILE_KEY = "f310b2300e73406785c490e867c4a7ab";
    private static final String MESSAGE_KEY = "77754e03c6c1473d834492972c9821a8";
    private static final String SCRIPT_KEY = "26d15a96814f4e0b8fc1f82ec0e26a46";

    private List<DemandModel> mData = null;

    public QueryDemand(int mode,String userId){
        ScorocodeSdk.initWith(APPLICATION_ID, CLIENT_KEY, null, FILE_KEY, MESSAGE_KEY, SCRIPT_KEY, null);
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
