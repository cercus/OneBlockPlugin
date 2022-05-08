package fr.cercusmc.oneblock.api.events;

import fr.cercusmc.oneblock.islands.Island;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class IslandDeleteEvent extends Event implements Cancellable {

    private boolean isCancelled;
    private static final HandlerList HANDLERS = new HandlerList();
    private Island island;

    public IslandDeleteEvent(Island island) {
        this.island = island;
    }

    public Island getIsland() {
        return island;
    }

    public void setIsland(Island island) {
        this.island = island;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHANDLERS() {
        return HANDLERS;
    }
}
