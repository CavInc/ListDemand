package cav.listdemand.data.storage.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import cav.listdemand.utils.ConstantManager;

/**
 * Created by cav on 07.07.17.
 */
public class DemandModelParcel implements Parcelable {

    //TODO возможно стоит сделать саму модель с Parcelable

    private DemandModel mModel;

    public DemandModelParcel(DemandModel model){
        mModel = model;
    }

    public DemandModel getModel() {
        return mModel;
    }

    protected DemandModelParcel(Parcel in) {

        String id = (in.readString());
        String operator_id = in.readString();
        String title = in.readString();
        String body = in.readString();
        boolean[] m = new boolean[1];
        in.readBooleanArray(m);
        boolean mclose = m[0];
        String comment = in.readString();
        mModel = new DemandModel(id,operator_id,title,body,mclose);
    }

    public static final Creator<DemandModelParcel> CREATOR = new Creator<DemandModelParcel>() {
        @Override
        public DemandModelParcel createFromParcel(Parcel in) {
            return new DemandModelParcel(in);
        }

        @Override
        public DemandModelParcel[] newArray(int size) {
            return new DemandModelParcel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(mModel.getId());
        parcel.writeString(mModel.getOperator_id()==null?"":mModel.getOperator_id());
        parcel.writeString(mModel.getTitle());
        parcel.writeString(mModel.getBody());
        parcel.writeBooleanArray(new boolean[]{mModel.isClose()});
        parcel.writeString(mModel.getComment());
       // parcel.writeValue(mModel);

    }
}
