package cav.listdemand.ui;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cav.listdemand.R;
import cav.listdemand.data.managers.DataManager;
import cav.listdemand.data.storage.models.DemandModel;
import cav.listdemand.data.storage.models.DemandModelParcel;
import cav.listdemand.utils.ConstantManager;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackUpdateDocument;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseUpdate;
import ru.profit_group.scorocode_sdk.scorocode_objects.Query;
import ru.profit_group.scorocode_sdk.scorocode_objects.Update;

public class ItemActivity extends AppCompatActivity {

    private static final String TAG = ConstantManager.TAG_PREFIX+" ITEM ACTIVITY";
    private DataManager mDataManager;

    private int mode;
    private DemandModel model;

    private TextView mItemTitle;
    private TextView mItemBody;
    private EditText mComment;

    private Button mOk;
    private Button mCancle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        mDataManager = DataManager.getInstance();

        mItemTitle = (TextView) findViewById(R.id.item_title);
        mItemBody = (TextView) findViewById(R.id.item_body);
        mComment = (EditText) findViewById(R.id.item_comment);

        mode = getIntent().getIntExtra(ConstantManager.ITEM_ACTIVITY_FLG,-1);
        model = ((DemandModelParcel) getIntent().getParcelableExtra(ConstantManager.ITEM_DATA)).getModel();

        mItemTitle.setText(model.getTitle());
        mItemBody.setText(model.getBody());

        mOk = (Button) findViewById(R.id.item_button_ok);
        mCancle = (Button) findViewById(R.id.item_button_cancel);
        if (mode == ConstantManager.NEW){
            mOk.setText("Взять заказ");
        }

        mOk.setOnClickListener(mOnClickListener);


    }

    // ставим документ на пользователя
    private void updateRec(String sessionID,String usedID,String docId,String comment){
        Query query = new Query("demand");
        query.equalTo("_id",docId);
        Update update = new Update()
                .set("operator_id",usedID)
                .set("comment",comment);
        query.updateDocument(update, new CallbackUpdateDocument() {
            @Override
            public void onUpdateSucceed(ResponseUpdate responseUpdate) {
                finish();
            }

            @SuppressLint("LongLogTag")
            @Override
            public void onUpdateFailed(String errorCode, String errorMessage) {
                Log.d(TAG,errorMessage);

            }
        });
    }

    @SuppressLint("LongLogTag")
    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String sesionID = mDataManager.getPreferensManager().getSessionId();
            String userID = mDataManager.getPreferensManager().getUserID();
            if (mode == ConstantManager.NEW){
                updateRec(sesionID,userID,model.getId(), String.valueOf(mComment.getText()));
            }
        }
    };
}
