package com.example.hrproject.trace.logtrace;

import com.example.hrproject.trace.TraceStatus;

public interface LogTrace {



    TraceStatus begin(String message);

    void end(TraceStatus status);

    void exception(TraceStatus status, Exception e);
}
