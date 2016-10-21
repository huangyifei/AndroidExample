package com.huangyifei.android.androidexample.mvplist.model;

import android.os.AsyncTask;

import com.huangyifei.android.androidexample.mvplist.base.IListModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangyifei on 16/10/21.
 */

public class UserListModel implements IListModel<UserModel> {
    public List<UserModel> mData;

    public UserListModel() {
        mData = new ArrayList<>();
    }

    @Override
    public List<UserModel> getData() {
        return mData;
    }


    public static void loadUserList(final LoadFinishListener listener) {
        if (listener == null) return;
        new AsyncTask<String, String, UserListModel>() {
            @Override
            protected UserListModel doInBackground(String... params) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
                UserListModel model = new UserListModel();
                for (int i = 0; i < 100; i++) {
                    model.mData.add(new UserModel("Name:" + i));
                }
                return model;
            }

            @Override
            protected void onPostExecute(UserListModel userListModel) {
                listener.onLoadFinish(userListModel);
            }
        }.execute("");
    }

    public interface LoadFinishListener {
        void onLoadFinish(UserListModel userListModel);
    }
}