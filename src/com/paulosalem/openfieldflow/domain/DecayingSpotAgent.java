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
import java.util.List;

/**
 * A color spot whose color diminishes until it disappears entirely.
 *
 * @author Paulo Salem
 */
public class DecayingSpotAgent extends Agent{

    protected double decayFactor;

    public DecayingSpotAgent(Vector position, double radius, Color color, double decayFactor) {
        super(position, radius, 0, new Vector(0,0,0), color);

        this.decayFactor = decayFactor;
        this.social = false;
    }

    
    @Override
    public void step(Iterable<Agent> all) {

       if(color.getAlpha() > 0.01){
            color = new Color(color.getRed(), color.getGreen(), color.getBlue(), (int)Math.floor(decayFactor * color.getAlpha()));
       }
       else{
           // Na particle is no longer visible, thus we may destroy it
           this.setAlive(false);
       }
    }

    @Override
    public void draw(Graphics2D canvas) {
        canvas.setPaint(color);
        canvas.fillRect(getPosition().getXAsInt(), getPosition().getYAsInt(), 1, 1);
    }

}
