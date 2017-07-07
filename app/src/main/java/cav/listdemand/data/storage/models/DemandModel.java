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
    private String mComment;

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

    public String getComment() {
        return mComment;
    }

    public void setId(String id) {
        mId = id;
    }

    public void setOperator_id(String operator_id) {
        mOperator_id = operator_id;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setBody(String body) {
        mBody = body;
    }

    public void setClose(Boolean close) {
        mClose = close;
    }

    public void setComment(String comment) {
        mComment = comment;
    }
}
