package fr.cercusmc.oneblock.utils;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class BossBarBuilder {

    private BossBar bossbar;
    private String title;
    private BarStyle barStyle;
    private BarColor barColor;
    private BarFlag barFlag;
    private double progress;

    public BossBarBuilder(String title, BarStyle barStyle, BarColor barColor, BarFlag barFlag, double progress) {
        if(title.length() > 64)
            this.title = title.substring(0, 64);
        else
            this.title = title;
        this.barStyle = barStyle;
        this.barColor = barColor;
        this.barFlag = barFlag;
        this.progress = progress;
        if(barFlag == null)
            this.bossbar = Bukkit.createBossBar(title, barColor, barStyle);
        else
            this.bossbar = Bukkit.createBossBar(title, barColor, barStyle, barFlag);
        this.bossbar.setProgress(progress);
    }

    public BossBarBuilder(String title, BarStyle barStyle, BarColor barColor, BarFlag barFlag) {
        this(title, barStyle, barColor, barFlag, 0.0);
    }

    public BossBarBuilder(String title, BarStyle barStyle, BarColor barColor) {
        this(title, barStyle, barColor, null, 0.0);
    }

    public BossBarBuilder addPlayer(UUID uuid) {
        if(this.bossbar.getPlayers().contains(uuid))
            return this;
        this.bossbar.addPlayer(Bukkit.getPlayer(uuid));
        return this;
    }

    public BossBarBuilder removePlayer(UUID uuid) {
        if(!this.bossbar.getPlayers().contains(uuid))
            return this;
        this.bossbar.removePlayer(Bukkit.getPlayer(uuid));
        return this;
    }

    public BossBarBuilder addPlayers(ArrayList<UUID> uuid) {
        uuid.forEach(k -> {
            if(!this.bossbar.getPlayers().contains(k)) {
                this.bossbar.addPlayer(Bukkit.getPlayer(k));
            }
        });

        return this;
    }

    public BossBarBuilder removePlayers(ArrayList<UUID> uuid) {
        uuid.forEach(k -> {
            if(this.bossbar.getPlayers().contains(k)) {
                this.bossbar.removePlayer(Bukkit.getPlayer(k));
            }
        });
        return this;
    }
    
    public double getProgress() {
        return progress;
    }

    public BossBarBuilder setProgress(double progress) {
        this.progress = progress;
        this.bossbar.setProgress(progress);
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BossBarBuilder setTitle(String title) {
        this.title = title;
        this.bossbar.setTitle(title);
        return this;
    }

    public BarStyle getBarStyle() {
        return barStyle;
    }

    public BossBarBuilder setBarStyle(BarStyle barStyle) {
        this.barStyle = barStyle;
        this.bossbar.setStyle(barStyle);
        return this;
    }

    public BarColor getBarColor() {
        return barColor;
    }

    public BossBarBuilder setBarColor(BarColor barColor) {
        this.barColor = barColor;
        this.bossbar.setColor(barColor);
        return this;
    }


    public BossBar build(){
        return this.bossbar;
    }
}
