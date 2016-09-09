package com.djcanadastudio.appifymap.Helper;

/**
 * Created by desenguo on 2016-09-08.
 */
public interface IConnector <T>{
    void done(T result) throws Exception;
}
