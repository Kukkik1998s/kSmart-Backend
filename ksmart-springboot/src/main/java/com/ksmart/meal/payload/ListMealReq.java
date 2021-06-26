package com.ksmart.meal.payload;

import com.ksmart.authen.model.User;

public class ListMealReq {
    User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
