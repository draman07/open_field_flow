/*
 * Open Field Flow - A particle system simulator in which particles 
 * flow through  and interact with a vector field.
 * 
 * Copyright (C) 2012  Paulo Salem (paulosalem@paulosalem.com)
 *
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 3 
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package com.paulosalem.openfieldflow.domain;

import com.paulosalem.openfieldflow.util.ColorScheme;
import java.util.HashSet;
import java.util.Set;

/**
 * An abstract scenario that defines the initial conditions of the simulation.
 * 
 * @author Paulo Salem
 */
abstract public class Scenario {

    protected Set<Agent> agents = new HashSet<Agent>();
    
    protected int width;
    
    protected int height;
    
    protected ColorScheme colorScheme;

    

    public Scenario(ColorScheme colorScheme, int width, int height) {
        this.colorScheme = colorScheme;
        this.width = width;
        this.height = height;
        
    }

    
    abstract public void preventConvergence();

    public Set<Agent> getAgents() {
        return agents;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

   
    
    
}
