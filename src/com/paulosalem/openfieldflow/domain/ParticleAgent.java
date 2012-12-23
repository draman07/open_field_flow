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

import com.google.common.collect.Iterables;
import com.paulosalem.openfieldflow.util.ColorHelper;
import com.paulosalem.openfieldflow.util.MathHelper;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * A base particle agent. As it is, it doesn't have many behaviors. Subclasses
 * should add more complex mechanisms.
 * 
 * @author Paulo Salem
 */
public class ParticleAgent extends Agent{

    protected Color baseGlowColor;

    protected Color glowColor;

    protected int glowRadius = 2;
    
    protected int neighbors = 0;


    public ParticleAgent(Vector position, double radius, double mass, Color color, Vector speed) {
        super(position, radius, mass, speed, color);
        
        this.baseColor = new Color(color.getRed(), color.getGreen(), color.getBlue());
        this.baseGlowColor = new Color(color.getRed(), color.getGreen(), color.getBlue(), 10);
        this.glowColor = new Color(color.getRed(), color.getGreen(), color.getBlue(), 10);
    }
    
    

    @Override
    public void step(Iterable<Agent> all) {
        
        //
        // Update this agent
        //
        updateContext(all);
        updatePosition(all);
        updateSpeed(all);
        
        /*
        Color c = new Color((int)Math.abs(this.speed.getVector().getYAsInt() * 50), (int)Math.abs(this.speed.getVector().getXAsInt() * 50), color.getBlue());
        color = c;
         */
/*
        neighbors = 0;
        List<Vector> speeds = new LinkedList<Vector>();
        List<Vector> positions = new LinkedList<Vector>();
        for(Agent a: all){
            if(!this.equals(a) && isWithinReach(a) && a instanceof ParticleAgent){
                
                ParticleAgent pa = (ParticleAgent) a;
                
                neighbors++;
                Vector s = a.getSpeed().clone();
                
                speeds.add(s);
                positions.add(a.getPosition());
                
            }
        }
        
        // Set new speed
        
        speeds.add(this.getSpeed());
        positions.add(this.getPosition());
        Vector avg = Vector.average(speeds);
        avg.multiply(0.5);
        this.getSpeed().add(avg); 
        
        Vector goToCenter = Vector.average(positions);
        goToCenter.subtract(position);
        
        if(goToCenter.length() < 10.0){
            goToCenter.multiply(-(goToCenter.length()/10.0));

        }
        else{
            goToCenter.multiply(1.0 / goToCenter.length());
        }
        
        
        this.getSpeed().add(goToCenter);
        */
         
        
        updateColor(all);
        updateGlow(all);

        createShadow();
    }
    
    @Override
    public void draw(Graphics2D canvas) {
        // TODO too slow...
        /*
        canvas.setPaint(glowColor);

        canvas.fillArc(this.getPosition().getXAsInt() - this.getGlowRadius(),
                       this.getPosition().getYAsInt()- this.getGlowRadius(),
                       2*this.getGlowRadius(),
                       2*this.getGlowRadius(),
                       0, 360);
        */
        canvas.setPaint(color);
        canvas.fillRect(getPosition().getXAsInt(), getPosition().getYAsInt(), 1, 1);
    }
        
    ///////////////////////////////////////////////////////////////////////////
    // State update methods
    ///////////////////////////////////////////////////////////////////////////
    
    protected void updateContext(Iterable<Agent> all){
        neighbors = Iterables.size(all);
    }
    
    protected void updateSpeed(Iterable<Agent> all){
  
        // Check maximum speed
        if(speed.length() > 5.0){
            this.getSpeed().normalize(1.0);
        }
    }
    
    protected void updateColor(Iterable<Agent> all){

        // Current color
        double alpha = neighbors / 100.0;
        setColor(ColorHelper.scale(baseColor, alpha));

        
    }

    protected void updateGlow(Iterable<Agent> all){
        double factor = 0.05;

        
        if(neighbors < 10){
            setGlowRadius(neighbors + 1);
            factor = 0.2 / (neighbors + 1);
        }
        else{
            setGlowRadius(10);
            factor = 0.2 / 11;
        }
        


        int alpha = MathHelper.enforceBoundaries((int)(255 * factor), 0, 255);

        setGlowColor(new Color(baseGlowColor.getRed(), baseGlowColor.getGreen(), baseGlowColor.getBlue(), alpha));
    }

    protected void createShadow(){
        DecayingSpotAgent dsa = new DecayingSpotAgent(position.clone(), radiusOfAction, color, 0.99);
        
        // TODO This is causing a massive slow down.... improve!
        //addNewChild(dsa);
    }

    public Color getGlowColor() {
        return glowColor;
    }

    public void setGlowColor(Color glowColor) {
        this.glowColor = glowColor;
    }

    public int getGlowRadius() {
        return glowRadius;
    }

    public void setGlowRadius(int glowRadius) {
        this.glowRadius = glowRadius;
    }

    
    

    
    
    
    
    
    
    
    

}
