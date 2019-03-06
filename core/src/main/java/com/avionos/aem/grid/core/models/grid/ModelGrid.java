package com.avionos.aem.grid.core.models.grid;

import com.avionos.aem.grid.api.models.grid.Breakpoint;
import com.avionos.aem.grid.api.models.grid.Grid;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.inject.Inject;
import java.util.List;

/**
 * Looks up the responsive breakpoints for the current page based either on the page properties or the
 * editable template configuration
 */
@Model(adaptables = SlingHttpServletRequest.class, adapters = Grid.class)
public class ModelGrid {

    @Self
    private SlingHttpServletRequest request;

    @Inject
    private Page currentPage;

    private Grid delegate;

    public List<Breakpoint> getBreakpoints() {
        return getDelegate().getBreakpoints();
    }

    public Breakpoint getBreakpoint(String type) {
        return getDelegate().getBreakpoint(type);
    }

    private Grid getDelegate() {
        if (delegate == null) {
            delegate = currentPage.adaptTo(Grid.class);
        }

        return delegate;
    }

}
