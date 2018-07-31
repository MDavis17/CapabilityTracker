package com.mdavis.capability_tracker;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CapabilityController {

    @RequestMapping("/capabilities")
    public Capability[] getCapabilities(@RequestParam(value="versions",defaultValue = "") String[] versions) {
//        return new Capability[] {new Capability("","0.0.0","")};
        return (new CapabilityCreator()).genCapabilities();
    }

//    @RequestMapping("/capabilities")
//    public Capability getCapability(@RequestParam(value="name",defaultValue = "") String name,@RequestParam(value="version",defaultValue = "0.0.0") String version,@RequestParam(value="valueThemes",defaultValue = "") String valueThemes) {
//        return new Capability(name,version,valueThemes);
//    }
}
