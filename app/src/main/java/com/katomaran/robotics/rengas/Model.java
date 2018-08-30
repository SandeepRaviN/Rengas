package com.katomaran.robotics.rengas;

import android.widget.Button;

/**
 * Created by Sandeep on 14-08-2018.
 */

public class Model {
    String title;
    String desc;
    int icon;

    public Model(String title, String desc, int icon) {
        this.title = title;
        this.desc = desc;
        this.icon = icon;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDesc() {
        return this.desc;
    }

    public int getIcon() { return this.icon; }

}
