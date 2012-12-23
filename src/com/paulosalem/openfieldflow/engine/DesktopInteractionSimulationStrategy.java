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

package com.paulosalem.openfieldflow.engine;

import com.paulosalem.openfieldflow.domain.ParticleAgent;
import com.paulosalem.openfieldflow.domain.Scenario;
import com.paulosalem.openfieldflow.domain.Vector;
import com.paulosalem.openfieldflow.domain.VectorAgent;
import java.awt.Color;

/**
 *
 * @author Paulo Salem
 */
public class DesktopInteractionSimulationStrategy extends SimulationStrategy{
    

    private int counter = 0;


    public DesktopInteractionSimulationStrategy(Scenario scenario, boolean preventConvergence, RepositionMethod repositionMethod) {
        super(scenario, preventConvergence, repositionMethod);
    }

    
    
    public void observeStart(Simulation s) {
        s.getAgents().addAll(scenario.getAgents());
    }
        
    
    
    public void observeUpdate(Simulation s) {
        
        // Fix particle position
        this.repositionAgents(s);

        if(preventConvergence){
            preventConvergence();
        }
    }

    public void observeStop(Simulation s) {
       // TODO nothing
    }


    private void preventConvergence(){
        counter++;

        if(counter > 3000){
            this.scenario.preventConvergence();
            counter = 0;
        }
    }

    

}
