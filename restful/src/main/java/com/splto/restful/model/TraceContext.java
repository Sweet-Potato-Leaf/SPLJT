package com.splto.restful.model;

import org.slf4j.MDC;

public class TraceContext {

    public static final String TRACE_ID = "traceId";

    public static void setTraceId(String traceId) {
        MDC.put(TRACE_ID, traceId);
    }

    public static String getTraceId() {
        return MDC.get(TRACE_ID);
    }

}
