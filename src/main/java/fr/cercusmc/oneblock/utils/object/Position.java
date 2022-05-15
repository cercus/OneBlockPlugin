package fr.cercusmc.oneblock.utils.object;

import org.bukkit.Location;
import org.bukkit.World;

public class Position {

    private int x;
    private int z;
    private int y;

    public Position(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getZ() {
        return z;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Position{x:"+this.x+", y:"+this.y+", z:"+this.z+"}";
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof Position))
            return false;
        Position pos = (Position) obj;
        return pos.getX() == this.x && pos.getY() == this.y;
    }

    public Location convertToLocation(World world){
        return new Location(world, this.getX(), this.getY(), this.getZ());
    }
}
