package com.avionos.aem.grid.core.models.grid;

import com.avionos.aem.grid.api.models.grid.Breakpoint;
import com.avionos.aem.grid.api.models.grid.Grid;
import com.day.cq.wcm.api.Page;
import com.google.common.collect.Lists;
import org.apache.sling.api.resource.Resource;

import java.util.*;

public class DefaultGrid implements Grid {

    private final Page page;

    private List<Breakpoint> breakpoints;

    public DefaultGrid(Page page) {
        this.page = page;
    }

    @Override
    public List<Breakpoint> getBreakpoints() {
        if (breakpoints != null) {
            return breakpoints;
        }

        breakpoints = Optional.ofNullable(getResponsiveConfigurationResource())
                .map(responsiveConfigurationResource -> responsiveConfigurationResource.getChild("breakpoints"))
                .map(Resource::listChildren)
                .map(breakpointsIterator -> {
                    List<Breakpoint> breakpointList = Lists.newArrayList();

                    breakpointsIterator.forEachRemaining(currentBreakpoint -> {
                        breakpointList.add(DefaultBreakpoint.forResource(currentBreakpoint));
                    });

                    return breakpointList;
                })
                .orElse(Lists.newArrayList());

        return breakpoints;
    }

    @Override
    public Breakpoint getBreakpoint(String type) {
        return getBreakpoints().stream()
                .filter(currentBreakpoint -> currentBreakpoint.getName().equals(type))
                .findFirst()
                .orElse(null);
    }

    private Resource getResponsiveConfigurationResource() {
        return Optional.ofNullable(page.getContentResource().getChild("cq:responsive"))
                .orElse(page.getTemplate().hasStructureSupport() ?
                        page.getContentResource().getResourceResolver().getResource(page.getTemplate().getPath() + "/structure/jcr:content/cq:responsive") :
                        null);
    }

}
