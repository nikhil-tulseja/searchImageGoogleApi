package com.doitunzo.imageSearch.utils;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Interface which is implemented by other classes to get service response, it sends requestID as
 * parameter along with the original volley response parameter.
 * PUT THIS INSIDE THE SAME PACKAGE AS OF CustomHttpRequest.java
 */
public interface HttpResponseListener
{
    void onResponseSuccess(Object response, int requestID);
    void onResponseError(VolleyError error, int requestID);
}


/*This class is used internally in CustomHttpRequest class*/
class InternalListener implements Response.ErrorListener, Response.Listener<Object>
{
    private final HttpResponseListener mListener;
    private final int mRequestID;

    public InternalListener(HttpResponseListener listener, int requestID)
    {
        mListener = listener;
        mRequestID = requestID;
    }

    @Override
    public void onErrorResponse(VolleyError error)
    {
        mListener.onResponseError(error, mRequestID);
    }

    @Override
    public void onResponse(Object response)
    {
        mListener.onResponseSuccess(response, mRequestID);
    }
}
