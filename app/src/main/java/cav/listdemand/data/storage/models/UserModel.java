package cav.listdemand.data.storage.models;

/**
 * Created by Kotov Alexandr on 31.01.17.
 *
 */
public class UserModel {
    private String mUser;
    private String mEmail;
    private String mId;

    public UserModel(String id,String user,String email){
        this.mId = id;
        this.mUser = user;
        this.mEmail = email;

    }

    public String getUser() {
        return mUser;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getId() {
        return mId;
    }
}
