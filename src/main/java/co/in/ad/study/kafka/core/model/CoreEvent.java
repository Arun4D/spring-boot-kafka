package co.in.ad.study.kafka.core.model;

import lombok.Data;

@Data
public class CoreEvent {
    private String eventId;
    private String eventName;
    private String eventGroup;
    private boolean isBusinessProcess;
}

