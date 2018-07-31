package com.mdavis.capability_tracker;

import org.springframework.stereotype.Component;

@Component
public class CapabilityCreator {

    public Capability[] genCapabilities() {
        Capability c1 = new Capability("AWS","1.2","Ops,Developer,Turnkey");
        Capability c2 = new Capability("K8s 1.10","1.1","Conformance");
        Capability c3 = new Capability("Azure","1.2","IaaS");
        Capability c4 = new Capability("Multi-AZ for workers","1.1","Resilience");
        Capability c5 = new Capability("Kubernetes Service Catalog","1.2","Turnkey, Developer");
        return new Capability[] {c1,c2,c3,c4,c5};
    }
}
