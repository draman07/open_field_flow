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

package com.paulosalem.openfieldflow.util;

import java.awt.Color;

/**
 * A class that provides several mechanisms for meaninful color manipulation.
 * 
 * @author Paulo Salem
 */
public class ColorHelper {

    /**
     * Creates a new color that is a scaled version of the specified
     * one according to the specified scaling constant. Can be
     * used to make colors brighter (positive scale factor) or
     * darker (negative scale factor). Formall, the new
     * color will be the original one multiplied by the scaling factor.
     * 
     * @param original The color to be scaled.
     * @param scalingFactor The scale factor.
     * @return A new color that is the scaled version of the original.
     */
    public static Color scale(Color original, double scalingFactor){

        int r = MathHelper.enforceBoundaries((int)(original.getRed() + scalingFactor * original.getRed()), 0, 255);
        int g = MathHelper.enforceBoundaries((int)(original.getGreen() + scalingFactor * original.getGreen()), 0, 255);
        int b = MathHelper.enforceBoundaries((int)(original.getBlue() + scalingFactor * original.getBlue()), 0, 255);
                
        return new Color(r, g, b);
    }
    
    public static Color random(){
        
        int r = (int)(255 * Math.random());
        int g = (int)(255 * Math.random());
        int b = (int)(255 * Math.random());
        
        return new Color(r,g,b);
    }
    
    
    
    
    
}
