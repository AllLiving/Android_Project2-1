package com.example.lixiang.mydiary.callback;

import com.avos.avoscloud.AVException;
import com.example.lixiang.mydiary.model.User;

/**
 * Created by thinkpad on 2016/4/24.
 */
public interface OnLogInCallback {
    void done(User user, AVException e);
}