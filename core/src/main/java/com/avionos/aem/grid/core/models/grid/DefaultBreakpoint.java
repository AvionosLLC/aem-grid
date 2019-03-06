package com.avionos.aem.grid.core.models.grid;

import com.avionos.aem.grid.api.models.grid.Breakpoint;
import org.apache.sling.api.resource.Resource;

public class DefaultBreakpoint implements Breakpoint {

    private final String name;
    private final String title;
    private final int width;

    public DefaultBreakpoint(String name, String title, int width) {
        this.name = name;
        this.title = title;
        this.width = width;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public int getWidth() {
        return width;
    }

    public static Breakpoint forResource(Resource resource) {
        return new DefaultBreakpoint(resource.getName(), resource.getValueMap().get("title", String.class), resource.getValueMap().get("width", 0));
    }

}
