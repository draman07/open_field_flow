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

import com.google.common.collect.Iterables;
import com.paulosalem.openfieldflow.domain.Agent;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Paulo Salem
 */
public class RegionsRegistry {

    private List<Agent>[][][] regions;

    private Map<Agent, List<Agent>> agentsRegions;
    
    private int regionSideSize;

    private int segmentsPerDimension;

    public RegionsRegistry(int sideSize, double fieldSize) {
        this.regionSideSize = sideSize;
        this.segmentsPerDimension = (int)Math.ceil(fieldSize / (double)sideSize);
        
        regions = new List[segmentsPerDimension][segmentsPerDimension][segmentsPerDimension];//new HashMap<Vector, List<Agent>>();
        agentsRegions = new HashMap<Agent, List<Agent>>();

        buildRegions();
    }
    
    public void buildRegions(){
        
        for(int i = 0; i < this.segmentsPerDimension; i = i + regionSideSize){
            for(int j = 0; j < this.segmentsPerDimension; j = j + regionSideSize){
                for(int k = 0; k < this.segmentsPerDimension; k = k + regionSideSize){
                    regions[i][j][k] = new LinkedList<Agent>();
                }
            }
        }
    }
    
    public void moveAgent(Agent a){
        if(agentsRegions.containsKey(a)){
            List<Agent> region = agentsRegions.get(a);
            region.remove(a);
        }

        int x = regionCoordinateXFor(a);
        int y = regionCoordinateYFor(a);
        int z = regionCoordinateZFor(a);
        
        List<Agent> region = agentsInRegion(x, y, z);//.get(v);
        

        if(region == null){
            regions[x][y][z] = region = new LinkedList<Agent>();
        }

        region.add(a);
        agentsRegions.put(a, region);
    }
    
    public Iterable<Agent> neighboors(Agent a){
        int x = regionCoordinateXFor(a);
        int y = regionCoordinateYFor(a);
        int z = regionCoordinateZFor(a);

        Iterable<Agent> region = Iterables.concat(agentsInRegion(x, y, z),
                agentsInRegion(x - 1, y, z),
                agentsInRegion(x, y - 1, z),
                agentsInRegion(x - 1, y - 1, z),
                agentsInRegion(x + 1, y, z),
                agentsInRegion(x, y + 1, z),
                agentsInRegion(x + 1, y + 1, z),
                agentsInRegion(x - 1, y + 1, z),
                agentsInRegion(x + 1, y - 1, z));

        

        // TODO add regions for the Z axis too if the space is 3D !! <---------------------
                
        return region;
    }

    public void removeAllAgents(List<Agent> as){
        for(Agent a: as){
            removeAgent(a);
        }
    }

    public void removeAgent(Agent a){
        agentsRegions.get(a).remove(a);
        agentsRegions.remove(a);

    }

    public int regionCoordinateXFor(Agent a){
        return (int) Math.floor(a.getPosition().getX() / regionSideSize);
    }

    public int regionCoordinateYFor(Agent a){
        return (int) Math.floor(a.getPosition().getY() / regionSideSize);
    }

    public int regionCoordinateZFor(Agent a){
        return (int) Math.floor(a.getPosition().getZ() / regionSideSize);
    }

    public List<Agent> agentsInRegion(int x, int y, int z){

        if(x >= 0 && y >= 0 && z >= 0 && x < this.segmentsPerDimension && y < this.segmentsPerDimension && z < this.segmentsPerDimension){
            if(regions[x][y][z] == null){
              regions[x][y][z] = new LinkedList<Agent>();
            }
            return regions[x][y][z];
        }
        else{
            return new LinkedList<Agent>(); // no agents found because the region is invalid
        }
    }

}
