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

import java.util.Collection;

/**
 * A mathematical vector.
 * 
 * @author Paulo Salem
 */
public class Vector {
    
    private double x;
    
    private double y;
    
    private double z;

    
    public Vector(double x, double y, double z) {
        setX(x);
        setY(y);
        setZ(z);
    }
    

    
    public void normalize(){
        normalize(1.0);
    }
    
    public void normalize(double length){
        double l = length();
        
        // Normalize
        setX(length * (getX() / l));
        setY(length * (getY() / l));
        setZ(length * (getZ() / l));
    }
    
    public double length(){
        return Math.sqrt(Math.pow(getX(), 2) + Math.pow(getY(), 2) + Math.pow(getZ(), 2));
    }
    
    public Vector divide(double value){
        setX(x / value);
        setY(y / value);
        setZ(z / value);

        return this;
    }
    
    public Vector multiply(double value){
        setX(x * value);
        setY(y * value);
        setZ(z * value);

        return this;
    }

    public Vector add(Vector v){
        addToX(v.getX());
        addToY(v.getY());
        addToZ(v.getZ());

        return this;
    }
    
    public Vector addToX(double value){
        setX(x + value);

        return this;
    }
    
    public Vector addToY(double value){
        setY(y + value);

        return this;
    }
    
    public Vector addToZ(double value){
        setZ(z + value);

        return this;
    }

    public Vector multiplyXBy(double value){
        setX(x*value);

        return this;
    }

    public Vector multiplyYBy(double value){
        setY(y*value);

        return this;
    }

    public Vector multiplyZBy(double value){
        setZ(z*value);

        return this;
    }
    
    public Vector subtract(Vector v){
        subtractFromX(v.getX());
        subtractFromY(v.getY());
        subtractFromZ(v.getZ());

        return this;
    }

    
    public Vector subtractFromX(double value){
        setX(x - value);

        return this;
    }
    
    public Vector subtractFromY(double value){
        setY(y - value);

        return this;
    }
    
    public Vector subtractFromZ(double value){
        setZ(z - value);

        return this;
    }
    
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
    
    public int getXAsInt(){
        return (int) Math.floor(x);
    }
    
    public int getYAsInt(){
        return (int) Math.floor(y);
    }
    
    public int getZAsInt(){
        return (int) Math.floor(z);
    }

    public void setX(double x) {
        if(!Double.isNaN(x)){
            this.x = x;
        }
        else{
            this.x = 0.0;
        }
    }

    public void setY(double y) {
        if(!Double.isNaN(y)){
            this.y = y;
        }
        else{
            this.y = 0.0;
        }

    }

    public void setZ(double z) {
        if(!Double.isNaN(z)){
            this.z = z;
        }
        else{
            this.z = 0.0;
        }
    }
    
    /**
     * Calculates a new vector that is the average of the
     * specified ones.
     * 
     * @param vectors The vectors to be averaged.
     * 
     * @return The average of the input vectors.
     */
    public static Vector average(Collection<Vector> vectors){
        
       if(vectors.size() == 0){
           throw new IllegalArgumentException("There must be one or more vectors in order to calculate their average value.");
       } 
        
       double x = 0, 
              y = 0, 
              z = 0;
        
        for(Vector v: vectors){
            x = x + v.getX();
            y = y + v.getY();
            z = z + v.getZ();
        }
        
        x = x / vectors.size();
        y = y / vectors.size();
        z = z / vectors.size();
        
        return new Vector(x, y, z);
    }
    
    
    @Override
    public boolean equals(Object o){
        if(o instanceof Vector){
            Vector v = (Vector)o;
            
            if(x == v.x && y == v.y && z == v.z){
                return true;
            }
        }
        
        return false;
    }
    
    @Override
    public int hashCode(){
        return (int) (x + y + z);
    }
    
    
    @Override
    public Vector clone(){
        return new Vector(x, y, z);
    }
    
    @Override
    public String toString(){
        return "(" + x + ", " + y + ", " + z + ")";
    }
}
