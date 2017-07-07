package cav.listdemand.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import cav.listdemand.R;
import cav.listdemand.data.storage.models.DemandModel;
import cav.listdemand.data.storage.models.DemandModelParcel;
import cav.listdemand.utils.ConstantManager;

public class ItemActivity extends AppCompatActivity {

    private int mode;

    private TextView mItemTitle;
    private TextView mItemBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        mItemTitle = (TextView) findViewById(R.id.item_title);
        mItemBody = (TextView) findViewById(R.id.item_body);

        mode = getIntent().getIntExtra(ConstantManager.ITEM_ACTIVITY_FLG,-1);
        DemandModel model = ((DemandModelParcel) getIntent().getParcelableExtra(ConstantManager.ITEM_DATA)).getModel();

        mItemTitle.setText(model.getTitle());
        mItemBody.setText(model.getBody());


    }
}
