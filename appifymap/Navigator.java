package com.djcanadastudio.appifymap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by desenguo on 2016-08-28.
 */
public class Navigator {
    public static void navigate(final Context from, final Class<?> to) {
        Intent newIntent = new Intent(from, to);
        from.startActivity(newIntent);
        ((Activity)from).finish();
    }
    public static void navigate(final Context from, Intent newIntent) {
        from.startActivity(newIntent);
        ((Activity)from).finish();
    }
}
