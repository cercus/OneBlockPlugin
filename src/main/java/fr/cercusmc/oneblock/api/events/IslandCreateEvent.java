package fr.cercusmc.oneblock.api.events;

import fr.cercusmc.oneblock.islands.Island;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class IslandCreateEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();
    private boolean isCancelled;

    private Island island;

    public IslandCreateEvent(Island island) {
        this.island = island;
        this.isCancelled = false;
    }

    public Island getIsland() {
        return island;
    }

    public static HandlerList getHANDLERS() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }


}
