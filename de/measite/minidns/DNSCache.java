package de.measite.minidns;

public interface DNSCache {
    DNSMessage get(Question question);

    void put(Question question, DNSMessage dNSMessage);
}
