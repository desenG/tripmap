package com.djcanadastudio.appifymap.Service.Rest;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;
/**
 * Created by desenguo on 2016-08-28.
 */
public class BusProvider {
    private static final Bus BUS = new Bus(ThreadEnforcer.ANY);

    private BusProvider() {
        // No instances.
    }

    public static Bus getInstance() {
        return BUS;
    }
}
