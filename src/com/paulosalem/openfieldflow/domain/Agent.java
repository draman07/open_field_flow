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

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * An abstract agent.
 * 
 * @author Paulo Salem
 */
abstract public class Agent {

    private static int last_id = 0;

    protected int id;
    
    protected Vector position = new Vector(0.0, 0.0, 0.0);
    
    protected double radiusOfAction = 10.0;
    
    protected double mass = 1.0;
    
    protected Vector speed;
    
    protected Color color;

    /**
     * Whether this agent interacts with other agents.
     * If this can be false, efficiency can be improved, because the possible
     * interactions with other agents can be ignored.
     */
    protected boolean social = true;

    /**
     * If the agent is alive, it should remain in the simulation. Else,
     * it should be removed.
     */
    protected boolean alive = true;
    
    /**
     * New children of this agent. The simulation engine must decide what
     * to do with them.
     */
    protected List<Agent> newChildren = new LinkedList<Agent>();
    protected Color baseColor;
    
    
    public Agent(Vector position, double radius, double mass, Vector speed, Color color) {
        this.position = position;
        this.radiusOfAction = radius;
        this.mass = mass;
        this.speed = speed;
        this.color = color;
        this.baseColor = color;

        this.id = last_id + 1;
        last_id++;
    }
    
    abstract public void step(Iterable<Agent> all);
    
    abstract public void draw(Graphics2D canvas);
    
    
    public boolean isWithinReach(Agent other){
        
        // Check if the other agent is within the sphere of influence
        // of this agent. 
        
        double x = other.getPosition().getX();
        double y = other.getPosition().getY();
        double z = other.getPosition().getZ();
        
        return isWithinReach(x, y, z);
    }
    
    public boolean isWithinReach(double x, double y, double z){

        double x0 = this.getPosition().getX();
        double y0 = this.getPosition().getY();
        double z0 = this.getPosition().getZ();

        double r = this.getRadiusOfAction();

        // If the other is within the sphere.
        if( Math.pow(x - x0, 2) + Math.pow(y - y0, 2) + Math.pow(z - z0, 2) <= Math.pow(r, 2)){
            return true;
        }
        
        return false;

    }
    

    /**
     * Calculates the distance from this agent to the specified one.
     * 
     * @param a The agent to be used in the distance calculation.
     * 
     * @return The distance from the specified agent.
     */
    public double distanceFrom(Agent a){
        // A vector from one agent to the other
        Vector v = new Vector(this.getPosition().getX() - a.getPosition().getX(),
                              this.getPosition().getY() - a.getPosition().getY(),
                              this.getPosition().getZ() - a.getPosition().getZ());
        
        return v.length();
    }
    
    
    
    
    ///////////////////////////////////////////////////////////////////////////
    // User interaction
    ///////////////////////////////////////////////////////////////////////////
    
    public void mouseDragged(Vector direction){
        
    }
    ///////////////////////////////////////////////////////////////////////////
    
    public int getId(){
        return id;
    }
    
    
    public double getRadiusOfAction() {
        return radiusOfAction;
    }

    public Vector getPosition() {
        return position;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public void addToSpeed(Vector speed) {
        this.speed.add(speed);
    }

    public Vector getSpeed() {
        return speed;
    }

    public void normalizeSpeed(double length) {
        this.speed.normalize(length);
    }

    public void setSpeed(Vector speed) {
        this.speed = speed;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isSocial() {
        return social;
    }

    public void setSocial(boolean social) {
        this.social = social;
    }

    



    /**
     * Returns the new children of this agent and then resets them
     * (i.e., so that the agent no longer has such children)
     * 
     * @return The new children.
     */
    public List<Agent> popNewChildren() {
         List<Agent> as = newChildren;

         newChildren = new LinkedList<Agent>();

         return as;
    }

    public void addNewChild(Agent child) {
        newChildren.add(child);
    }

    protected void updatePosition(Iterable<Agent> all) {
        // Calculate new position
        position.addToX(speed.getX());
        position.addToY(speed.getY());
        position.addToZ(speed.getZ());
    }

    public Color getBaseColor() {
        return baseColor;
    }

    public void setBaseColor(Color baseColor) {
        this.baseColor = baseColor;
    }
    
    
    
    

}
