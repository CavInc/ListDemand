package cav.listdemand.data.storage.models;

/**
 * Created by Kotov Alexandr on 31.01.17.
 *
 */
public class DemandModel {
    private String mId;
    private String mOperator_id;
    private String mTitle;
    private String mBody;
    private Boolean mClose;

    public DemandModel(String id,String operator_id,String title,String body,Boolean close_demand){
        mId = id;
        mOperator_id = operator_id;
        mTitle = title;
        mBody = body;
        mClose = close_demand;

    }

    public String getId() {
        return mId;
    }

    public String getOperator_id() {
        return mOperator_id;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getBody() {
        return mBody;
    }

    public Boolean isClose() {
        return mClose;
    }
}
