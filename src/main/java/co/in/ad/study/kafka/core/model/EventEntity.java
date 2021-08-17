package co.in.ad.study.kafka.core.model;

@lombok.Data
public class EventEntity<T> {
    private CoreEvent coreEventMetadata;
    private T data;
    private String key;
}
