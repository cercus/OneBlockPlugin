package fr.cercusmc.oneblock.utils;

import fr.cercusmc.oneblock.OneBlock;

public class SpiralGrid {
    /**
     * Cette classe permet de calculer la position suivante d'île, selon un systeme de spiral :
     *
     * 5  4  3
     * 6  1  2
     * 7  8  9
     *
     */

    public static Position up(Position position) {
        return new Position(position.getX(), position.getY(), position.getZ()+ OneBlock.getFileConfig().getMaxRadius());
    }

    public static Position down(Position position) {
        return new Position(position.getX(), position.getY(), position.getZ()-OneBlock.getFileConfig().getMaxRadius());
    }

    public static Position left(Position position) {
        return new Position(position.getX() - OneBlock.getFileConfig().getMaxRadius(), position.getY(), position.getZ());
    }

    public static Position right(Position position) {
        return new Position(position.getX()+OneBlock.getFileConfig().getMaxRadius(), position.getY(), position.getZ());
    }


    public static Position findNext(Position pos) {
        if(pos.getX() == 0 && pos.getZ() == 0)
            return new Position(OneBlock.getFileConfig().getMaxRadius(), 73, 0);

        Position coords = new Position(pos.getX(), pos.getY(), pos.getZ());
        //Position coords = new Position(0, 73, 0);
        if(Math.abs(pos.getX()) == Math.abs(pos.getZ())) {
            if (pos.getX() > 0 && pos.getZ() > 0)
                coords = left(pos);
            else if (pos.getX() < 0 && pos.getZ() > 0)
                coords = down(pos);
            else if (pos.getX() < 0 && pos.getZ() < 0)
                coords = up(pos);
            else if (pos.getX() > 0 && pos.getZ() < 0)
                coords = right(pos);
        } else {
            if (pos.getX() > pos.getZ() && Math.abs(pos.getX()) > Math.abs(pos.getZ()))
                coords = up(pos);
            else if (pos.getX() < pos.getZ() && Math.abs(pos.getX()) < Math.abs(pos.getZ()))
                coords = left(pos);
            else if (pos.getX() < pos.getZ() && Math.abs(pos.getX()) > Math.abs(pos.getZ()))
                coords = down(pos);
            else if (pos.getX() > pos.getZ() && Math.abs(pos.getX()) < Math.abs(pos.getZ()))
                coords = right(pos);
        }
        return coords;
    }

    public static Position spiral_pattern2(int num) {
        int dx = 0;
        int dz = 1;
        int segmentLength = 1;
        int x = 0;
        int z = 0;
        int segmentPassed = 0;
        if(num == 0) {
            System.out.println("(" + x + ";" + z + ")");
            return new Position(x, 73, z);
        }

        for(int n = 0; n < num; ++n){
            x += dx;
            z += dz;
            ++segmentPassed;
            if(segmentLength == segmentPassed) {
                segmentPassed = 0;
                int buffer = dz;
                dz = -dx;
                dx = buffer;
                if(dx == 0)
                    ++segmentLength;
            }
        }
        System.out.println("(" + x + ";" + z + ")");
        return new Position(x, 73, z);
    }

    public static Position spiral_pattern(int num) {
        Position pos = new Position(0, 73, 0);
        if(num == 0) {
            System.out.println("num == 0"+ pos);
            return pos;
        }
        for(int i = 0; i < num-1; i++) {
            pos = findNext(pos);
            System.out.println(pos);
        }
        return pos;
    }
}
