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
import com.paulosalem.openfieldflow.util.ColorScheme;
import java.awt.Color;

/**
 * A scenario that establishes an interactive vector field.
 * 
 * @author Paulo Salem
 */
public class VectorFieldScenario extends Scenario{
    
    public enum FieldCondition {RANDOM, STATIC, UP, DOWN, LEFT, RIGHT}

    public VectorFieldScenario(int numberOfParticles, int fieldWidth, int fieldHeight, 
            int vectorSpacing, double resistanceFactor, double influenceFactor,
            FieldCondition fieldCondition, ColorScheme colorScheme) {

        super(colorScheme, fieldWidth, fieldHeight);
        
        
        // Build vectors
        for(int i = 0; i < fieldWidth; i = i + vectorSpacing){
            for(int j = 0; j < fieldHeight; j = j + vectorSpacing){
                
                Vector position = new Vector(i, j, 0);
                
                Vector direction = new Vector(0,0,0);
                if(fieldCondition == FieldCondition.RANDOM){
                  direction = new Vector(0.1 * Math.random()-0.1 * Math.random(),0.1 * Math.random()-0.1 * Math.random(),0);
                }
                else if(fieldCondition == FieldCondition.STATIC){
                  direction = new Vector(0.0,0.0,0.0);
                }
                else if(fieldCondition == FieldCondition.UP){
                  direction = new Vector(0.0,-1.0,0.0);
                }
                else if(fieldCondition == FieldCondition.DOWN){
                  direction = new Vector(0.0,1.0,0.0);
                }
                else if(fieldCondition == FieldCondition.LEFT){
                  direction = new Vector(-1.0,0.0,0.0);
                }
                else if(fieldCondition == FieldCondition.RIGHT){
                  direction = new Vector(1.0,0.0,0.0);
                }
                
                Color color = ColorHelper.scale(colorScheme.getHeatColor(), -0.6);

                VectorAgent va = new VectorAgent(position, 20.0, color , direction, 10, resistanceFactor, influenceFactor);

                agents.add(va);
            }
        }
        
        // Launch particles
        for(int i = 0; i < numberOfParticles; i++){
            
            Vector position = new Vector(fieldWidth * Math.random(), fieldHeight * Math.random(), 1);

            Vector speed = new Vector(1 * Math.random() - 1 * Math.random(), 1 * Math.random() - 1 * Math.random(), 0);
            if (fieldCondition == FieldCondition.RANDOM) {
                speed = new Vector(1 * Math.random() - 1 * Math.random(), 1 * Math.random() - 1 * Math.random(), 0);
            } else if (fieldCondition == FieldCondition.STATIC) {
                speed = new Vector(0.0, 0.0, 0.0);
            } else if (fieldCondition == FieldCondition.UP) {
                speed = new Vector(0.0, -1.0, 0.0);
            } else if (fieldCondition == FieldCondition.DOWN) {
                speed = new Vector(0.0, 1.0, 0.0);
            } else if (fieldCondition == FieldCondition.LEFT) {
                speed = new Vector(-1.0, 0.0, 0.0);
            } else if (fieldCondition == FieldCondition.RIGHT) {
                speed = new Vector(1.0, 0.0, 0.0);
            }
            
            Color color = colorScheme.getHeatColor();

            ParticleAgent a = new ParticleAgent(position, 10.0, 1.0, color, speed);
            
            agents.add(a);
        }
        
    }

    public void preventConvergence(){

        // To prevent convergence, we will change the vector agents

        for(Agent a: agents){
          if(a instanceof VectorAgent){
              VectorAgent va = (VectorAgent) a;

              Vector direction = va.getDirection();

              // original length
              double length = direction.length();

              // Assign a random direction
              direction = new Vector(0.1 * Math.random()-0.1 * Math.random(),0.1 * Math.random()-0.1 * Math.random(),0);

              // The size will be the same
              direction.normalize(length);

              va.setDirection(direction);
          }
        }
    }
    
}
