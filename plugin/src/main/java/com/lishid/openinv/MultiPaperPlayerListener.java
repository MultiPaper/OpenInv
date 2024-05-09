/*
 * Copyright (C) 2011-2022 lishid. All rights reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.lishid.openinv;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;
import puregero.multipaper.event.player.PlayerJoinExternalServerEvent;
import puregero.multipaper.event.player.PlayerLeaveExternalServerEvent;

import java.util.Objects;

record MultiPaperPlayerListener(OpenInv plugin) implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    private void onExternalPlayerJoin(@NotNull PlayerJoinExternalServerEvent event) {
        plugin.getServer().getScheduler().runTaskLater(plugin, () -> plugin.setPlayerOnline(Objects.requireNonNull(Bukkit.getPlayer(event.getPlayerUniqueId()))), 1);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    private void onExternalPlayerQuit(@NotNull PlayerLeaveExternalServerEvent event) {
        plugin.setPlayerOffline(event.getPlayerUniqueId());
    }

}
