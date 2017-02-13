package me.jaimemartz.lobbybalancer.ping;

import me.jaimemartz.lobbybalancer.configuration.ConfigEntries;

public final class ServerStatus {
    private final String description;
    private final int online, maximum;

    public ServerStatus(String description, int online, int maximum) {
        this.description = description;
        this.online = online;
        this.maximum = maximum;
    }

    public String getDescription() {
        return description;
    }

    public int getOnlinePlayers() {
        return online;
    }

    public int getMaximumPlayers() {
        return maximum;
    }

    public boolean isAccessible() {
        if (maximum == 0) {
            return false;
        }

        for (String pattern : ConfigEntries.SERVER_CHECK_MARKER_DESCS.get()) {
            if (description.matches(pattern)) {
                return false;
            }
        }

        if (online >= maximum) {
            return false;
        }

        return true;
    }
}