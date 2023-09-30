package net.zhuruoling.sw;

import net.zhuruoling.omms.central.whitelist.PlayerAlreadyExistsException;
import net.zhuruoling.omms.central.whitelist.PlayerNotFoundException;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class WhitelistData {
    private CopyOnWriteArrayList<String> names = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<String> players = new CopyOnWriteArrayList<>();

    public WhitelistData(CopyOnWriteArrayList<String> names, CopyOnWriteArrayList<String> players) {
        this.names.addAll(names);
        this.players.addAll(players);
    }

    public boolean contains(String player) {
        return players.contains(player);
    }

    public List<String> getPlayers() {
        return players;
    }

    public void addPlayer(String name, String player) throws PlayerAlreadyExistsException {
        if (players.contains(player)) {
            throw new PlayerAlreadyExistsException(name, player);
        }
        players.add(player);
    }

    public void removePlayer(String name, String player) throws PlayerNotFoundException {
        if (!players.contains(player)) {
            throw new PlayerNotFoundException(name, player);
        }
        players.remove(player);
    }

    public void saveModifiedBuffer() {
        Data.instace.save();
    }

    public void removeStubWhitelist(String name) {
        names.remove(name);
        saveModifiedBuffer();
    }

    public CopyOnWriteArrayList<String> getNames() {
        return names;
    }
}
