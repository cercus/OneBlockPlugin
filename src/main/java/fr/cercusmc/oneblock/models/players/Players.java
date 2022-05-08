package fr.cercusmc.oneblock.models.players;

import fr.cercusmc.oneblock.islands.Island;

import java.util.List;

public class Players {

    private List<Island> islands;
    private int nbIsland;

    public Players(List<Island> uuids, int nbIsland) {

        this.islands = uuids;
        this.nbIsland = nbIsland;
    }

    public List<Island> getIslands() {
        return islands;
    }

    public void setIslands(List<Island> uuids) {
        this.islands = uuids;
    }

    public void setNbIsland(int nbIsland) {
        this.nbIsland = nbIsland;
    }

    public int getNbIsland() {
        return nbIsland;
    }
}
