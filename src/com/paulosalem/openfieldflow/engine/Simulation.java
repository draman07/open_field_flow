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
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Paulo Salem
 */
public class Simulation implements Runnable{
    
    private RegionsRegistry regionsRegistry;
    
    private boolean pause;
    
    private boolean stop;
    
    private Set<SimulationObserver> observers = new HashSet<SimulationObserver>();
    
    private List<Agent> agents;
    

    public Simulation(int regionSideSize, int largerSide) {
        this.pause = false;
        this.stop = false;
        this.agents =  Collections.synchronizedList(new LinkedList<Agent>()); // This list will be accessed concurrently
        
        regionsRegistry = new RegionsRegistry(regionSideSize, largerSide);
    }

    @Override
    public synchronized void run() {
        
        start();
        
        while(!stop){
            if(!pause){
                try {
                    step();
                    this.wait(10);
                } catch (InterruptedException ex) {
                    // Nothing
                }
            }
        }
        
        notifyStop();
    }
    
    
    protected void step(){

        List<Agent> dead = new LinkedList<Agent>();
        List<Agent> born = new LinkedList<Agent>();

        synchronized(agents){

            // Update each agent
            for(Agent a: agents){

                // Move the agent to the appropriate region
                regionsRegistry.moveAgent(a);

                // Update the agent according to the neighbors in its region,
                // if it is a social agent...
                if(a.isSocial()){
                    a.step(regionsRegistry.neighboors(a));
                }
                // ...otherwise update the agent ignoring neighbors
                else{
                    a.step(null);
                }

                //a.step(agents);

                // Get agent's children
                born.addAll(a.popNewChildren());

                // Mark for deletion, in case the agent is no longer alive
                if(!a.isAlive()){
                    dead.add(a);
                }
            }

            // Delete agents marked as dead
            agents.removeAll(dead);
            regionsRegistry.removeAllAgents(dead);

            // Add newborn agents
            agents.addAll(born);
        }
        
        notifyUpdate();
    }
    
    public void start(){
        pause = false;
        notifyStart();
    }
    
    public void pause(){
        // TODO ??
    }
    
    public void stop(){
        stop = true;
    }

    public List<Agent> getAgents() {
        return agents;
    }
    
    public void addAgent(Agent a){
        agents.add(a);
    }

    public void addAgents(Collection<Agent> as){
        for(Agent a: as){
            addAgent(a);
        }
    }
    
    protected void notifyStart(){
        for(SimulationObserver o: observers){
            o.observeStart(this);
        }
    }
    
    protected void notifyUpdate(){
        for(SimulationObserver o: observers){
            o.observeUpdate(this);
        }
    }
    
    protected void notifyStop(){
        for(SimulationObserver o: observers){
            o.observeStop(this);
        }
    }
    
    public void attachObserver(SimulationObserver observer){
        observers.add(observer);
    }
    
    public void deattachObserver(SimulationObserver observer){
        observers.remove(observer);
    }

}
