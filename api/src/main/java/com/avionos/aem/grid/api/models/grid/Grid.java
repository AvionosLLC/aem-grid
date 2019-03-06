package com.avionos.aem.grid.api.models.grid;

import java.util.List;

public interface Grid {

    List<Breakpoint> getBreakpoints();

    Breakpoint getBreakpoint(String type);

}
