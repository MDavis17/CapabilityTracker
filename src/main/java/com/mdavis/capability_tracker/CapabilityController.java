package com.mdavis.capability_tracker;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CapabilityController {

    @RequestMapping("/capabilities")
    public Capability[] getCapabilities(@RequestParam(value="versions",defaultValue = "") String[] versions,@RequestParam(value="themes",defaultValue = "") String[] themes) {
        return (new CapabilityCreator()).genCapabilities(versions,themes);
    }
}
