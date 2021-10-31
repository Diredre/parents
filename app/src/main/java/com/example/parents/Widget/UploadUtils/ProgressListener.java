package com.example.parents.Widget.UploadUtils;

public interface ProgressListener {
    void onProgress(long currentBytes, long contentLength, boolean done);
}