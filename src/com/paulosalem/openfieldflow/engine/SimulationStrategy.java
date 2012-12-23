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

import com.paulosalem.openfieldflow.domain.Agent;
import com.paulosalem.openfieldflow.domain.Scenario;
import com.paulosalem.openfieldflow.domain.Vector;
import java.awt.event.MouseEvent;

/**
 *
 * @author Paulo Salem
 */
abstract public class SimulationStrategy implements SimulationObserver{

    /**
     * How to reposition an agent when it leaves the visible canvas.
     */
    public enum RepositionMethod {Torus, Destroy, Bounce}

    protected Scenario scenario;
    
    protected Vector lastMousePosition;

    protected boolean preventConvergence;

    protected RepositionMethod repositionMethod = RepositionMethod.Torus;
    
     public SimulationStrategy(Scenario scenario, boolean preventConvergence, RepositionMethod repositionMethod) {
        this.scenario = scenario;
        this.preventConvergence = preventConvergence;
        this.repositionMethod = repositionMethod;

    }
     
     
     protected void repositionAgents(Simulation s){
         
         for(Agent a: s.getAgents()){


             // Fix x coordinate
             if(a.getPosition().getX() > scenario.getWidth()){

                 if(this.repositionMethod == RepositionMethod.Torus){
                    a.getPosition().setX(0);
                 }
                 else if(this.repositionMethod == RepositionMethod.Destroy){
                    a.setAlive(false);
                 }
                 else if(this.repositionMethod == RepositionMethod.Bounce){
                     a.getPosition().setX(scenario.getWidth());
                     a.getSpeed().multiplyXBy(-0.9);

                 }

             }
             else if(a.getPosition().getX() < 0){

                 if(this.repositionMethod == RepositionMethod.Torus){
                    a.getPosition().setX(scenario.getWidth());
                 }
                 else if(this.repositionMethod == RepositionMethod.Destroy){
                     a.setAlive(false);
                 }
                 else if(this.repositionMethod == RepositionMethod.Bounce){
                     a.getPosition().setX(0);
                     a.getSpeed().multiplyXBy(-0.9);
                 }
             }
             
             // Fix y coordinate
             if(a.getPosition().getY() > scenario.getHeight()){

                 if(this.repositionMethod == RepositionMethod.Torus){
                    a.getPosition().setY(0);
                 }
                 else if(this.repositionMethod == RepositionMethod.Destroy){
                     a.setAlive(false);
                 }
                 else if(this.repositionMethod == RepositionMethod.Bounce){
                     a.getPosition().setY(scenario.getHeight());
                     a.getSpeed().multiplyYBy(-0.9);
                 }
             }
             else if(a.getPosition().getY() < 0){

                 if(this.repositionMethod == RepositionMethod.Torus){
                    a.getPosition().setY(scenario.getHeight());
                 }
                 else if(this.repositionMethod == RepositionMethod.Destroy){
                     a.setAlive(false);
                 }
                 else if(this.repositionMethod == RepositionMethod.Bounce){
                     a.getPosition().setY(0);
                     a.getSpeed().multiplyYBy(-0.9);
                 }

             }
             
             // Fix z coordinate
             // TODO
         }
     }

    public void mouseDragged(MouseEvent e, Simulation s) {
        
        double x = e.getX();
        double y = e.getY();
        double z = 0.0;
        
        Vector currentMousePosition = new Vector(x, y, z);

        // Ensure that there is a last position
        if(lastMousePosition == null){
            lastMousePosition = currentMousePosition.clone();
        }
        
        currentMousePosition.subtract(lastMousePosition);
        currentMousePosition.normalize(6.0);
        /*if(currentMousePosition.length() > 10.0){
          currentMousePosition.normalize(10.0);
        }*/
        // TODO
        
        // The mouse influences the agents nearby
        for(Agent a: s.getAgents()){
            
            if(a.isWithinReach(x, y, z)){
                a.mouseDragged(currentMousePosition.clone());
            
            }
        }
        
        // Update the last mouse position
        lastMousePosition = new Vector(x, y, z);
        
    }


     
     
}
