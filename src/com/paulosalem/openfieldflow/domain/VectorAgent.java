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

import com.paulosalem.openfieldflow.util.ColorHelper;
import com.paulosalem.openfieldflow.util.MathHelper;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * An agent that represents a vector in a vector field and that interacts with
 * agents nearby.
 * 
 * @author Paulo Salem
 */
public class VectorAgent extends Agent{
    
    /**
     * Where the vector points to.
     */
    private Vector direction;
    
    /**
     * The maximum length the direction vector may have.
     */
    private double maxDirectionLength = 10.0;
    
    /**
     * A factor that defines how much this vector shall be influenced
     * by nearby agents.
     */
    private double resistanceFactor = 0.01;
    
    /**
     * A factor that defines how much of the vector shall influence
     * the nearby agents.
     */
    private double influenceFactor = 0.1;

    protected Color baseGlowColor;

    protected Color glowColor;

    
    /**
     * A number between 0.0 and 1.0 that indicates how strong is the memory
     * of past collisions.
     */
    protected double collisionsMemory = 0.0;

    protected double collisionsMemoryDecayFactor = 0.99;


    protected BasicStroke regularStroke = new BasicStroke(1);
    
    protected BasicStroke glowStroke = new BasicStroke(3); //new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL);

    public VectorAgent(Vector position, double radius, Color color, Vector direction) {
        super(position, radius, 1.0, new Vector(0,0,0), color);
        
        this.direction = direction;

        build();
    }
    
    public VectorAgent(Vector position, double radius, Color color, Vector direction,
            double maxDirectionLength, double resistanceFactor, double influenceFactor) {
        super(position, radius, 1.0, new Vector(0,0,0), color);
        
        this.direction = direction;
        this.maxDirectionLength = maxDirectionLength;
        this.resistanceFactor = resistanceFactor;
        this.influenceFactor = influenceFactor;

        build();
    }

    private void build(){
        this.baseGlowColor = ColorHelper.scale(color, 1.0);
        this.glowColor = baseGlowColor;
    }
    

    @Override
    public void step(Iterable<Agent> others) {

        for(Agent a: others){
            if(a instanceof ParticleAgent && direction != null && isWithinReach(a)){
                
                ParticleAgent pa = (ParticleAgent)a;
                
                //
                // Influence
                //
                
                Vector v = direction.clone();
                v.multiply(influenceFactor);
                pa.getSpeed().add(v);
                
                
                //
                // Be influenced
                //
                
                v = pa.getSpeed().clone();
                v.multiply(resistanceFactor);
                direction.add(v);
                collisionsMemory += 0.01;
                
                //
                // Fix length
                //
                
                if(direction.length() > maxDirectionLength){
                    direction.normalize(maxDirectionLength);
                }
                
                
            }
        }

        // Enforce memory boundary
        if(collisionsMemory > 1.0){
            collisionsMemory = 1.0;
        }

        // Apply memory decay factor
        collisionsMemory = collisionsMemory * collisionsMemoryDecayFactor;
        
        updateColor(others);
        updateGlow(others);
        
    }
    
    
    protected void updateColor(Iterable<Agent> all){
        double factor = collisionsMemory / 2.0;

       setColor(ColorHelper.scale(baseColor, factor));
    }

    protected void updateGlow(Iterable<Agent> all){
        double factor = collisionsMemory / 1.5;

        int alpha = MathHelper.enforceBoundaries((int)(255 * factor), 0, 255);

        setGlowColor(new Color(baseGlowColor.getRed(), baseGlowColor.getGreen(), baseGlowColor.getBlue(), alpha));
    }

    @Override
    public void draw(Graphics2D canvas) {
        // TODO too slow...
        /*
        canvas.setStroke(glowStroke);
        canvas.setPaint(glowColor);
        canvas.drawLine(
                position.getXAsInt(),
                position.getYAsInt(),
                position.getXAsInt() + direction.getXAsInt(),
                position.getYAsInt() + direction.getYAsInt());

        canvas.setStroke(regularStroke);
         */
         
        canvas.setPaint(color);
        canvas.drawLine(
                position.getXAsInt(), 
                position.getYAsInt(), 
                position.getXAsInt() + direction.getXAsInt(), 
                position.getYAsInt() + direction.getYAsInt());
        
    }
    
    public void mouseDragged(Vector direction){
      setDirection(direction);    
    }

    public void setDirection(Vector direction) {
        this.direction = direction;
    }

    public Vector getDirection() {
        return direction;
    }

    public Color getGlowColor() {
        return glowColor;
    }

    public void setGlowColor(Color glowColor) {
        this.glowColor = glowColor;
    }


    
    

}
