package me.borawski.hcf.session;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Transient;

import me.borawski.hcf.punishment.Punishment;
import me.borawski.hcf.punishment.Punishment.Type;

@Entity(value = "players", noClassnameStored = true)
public class Session {

    @Id
    private UUID uuid;

    @Indexed
    private String name;

    private Rank rank;

    private int tokens;
    private int level;
    private int exp;

    @Property("first_login")
    private long firstLogin;

    @Property("last_login")
    private long lastLogin;

    @Property("total_played")
    private long totalPlayed;

    @Indexed
    private String ip;

    private List<String> achievements;

    private List<UUID> friends;

    private Map<String, String> settings;

    @Transient
    private List<Punishment> activePunishments;

    public UUID getUniqueId() {
        return uuid;
    }

    public void setUniqueId(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public int getTokens() {
        return tokens;
    }

    public void setTokens(int tokens) {
        this.tokens = tokens;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public long getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(long firstLogin) {
        this.firstLogin = firstLogin;
    }

    public long getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(long lastLogin) {
        this.lastLogin = lastLogin;
    }

    public long getTotalPlayed() {
        return totalPlayed;
    }

    public void setTotalPlayed(long totalPlayed) {
        this.totalPlayed = totalPlayed;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public List<String> getAchievements() {
        return achievements;
    }

    public void setAchievements(List<String> achievements) {
        this.achievements = achievements;
    }

    public List<UUID> getFriends() {
        return friends;
    }

    public void setFriends(List<UUID> friends) {
        this.friends = friends;
    }

    public Map<String, String> getSettings() {
        return settings;
    }

    public void setSettings(Map<String, String> settings) {
        this.settings = settings;
    }

    public List<Punishment> getActivePunishments() {
        return activePunishments;
    }

    public void setActivePunishments(List<Punishment> activePunishments) {
        this.activePunishments = activePunishments;
    }

    public Punishment isBanned() {
        for (Punishment p : activePunishments) {
            if (p.getType() == Type.BAN) {
                return p;
            }
        }
        return null;
    }

    public Punishment isMuted() {
        for (Punishment p : activePunishments) {
            if (p.getType() == Type.MUTE) {
                return p;
            }
        }
        return null;
    }

    public void sendMessage(String message) {
        Player p = Bukkit.getPlayer(uuid);
        if (p != null) {
            p.sendMessage(message);
        }
    }

}
