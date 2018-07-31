package com.mdavis.capability_tracker;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Vector;

@Component
public class CapabilityCreator {

    public Capability[] genCapabilities(String[] versions, String[] themes) {
        Capability c1 = new Capability("AWS","1.2.0","Ops,Developer,Turnkey");
        Capability c2 = new Capability("K8s 1.10","1.1.0","Conformance");
        Capability c3 = new Capability("Azure","1.2.0","IaaS");
        Capability c4 = new Capability("Multi-AZ for workers","1.1.0","Resilience");
        Capability c5 = new Capability("Kubernetes Service Catalog","1.2.0","Turnkey, Developer");
        Capability c6 = new Capability("Azure","1.2.0","IaaS");
        Capability c7 = new Capability("Increase the number of clusters that can be created with NSX-T support","1.1.2","Multi-cluster");
        Capability c8 = new Capability("SSL need","1.1.1","Security");
        Capability c9 = new Capability("Network Isolation","1.0.0","Security");
        Capability c10 = new Capability("Foo","1.0.1","Ops");
        Capability[] capabilities = new Capability[] {c1,c2,c3,c4,c5,c6,c7,c8,c9,c10};

        Vector<Capability> results = new Vector<Capability>();


        for(Capability capability: capabilities) {
            // version match
            Version cap_version = capability.getVersion();
            if(Arrays.asList(versions).contains(cap_version.toString())) {
                results.add(capability);
            }
        }

//        for(String version: versions) {
//            Version v = new Version(version);
//            for(Capability capability: capabilities) {
//                if(capability.getVersion().equals(v))
//                    results.add(capability);
//            }
//        }

//        return new Capability[] {c1,c2,c3,c4,c5,c6,c7,c8,c9,c10};
        return results.toArray(new Capability[results.size()]);
    }
}
