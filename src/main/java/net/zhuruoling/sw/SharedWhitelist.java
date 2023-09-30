package net.zhuruoling.sw;

import net.zhuruoling.omms.central.whitelist.PlayerAlreadyExistsException;
import net.zhuruoling.omms.central.whitelist.PlayerNotFoundException;
import net.zhuruoling.omms.central.whitelist.Whitelist;

import java.util.List;

public class SharedWhitelist extends Whitelist {
    String name;
    WhitelistData data;

    public SharedWhitelist(String name, WhitelistData data) {
        this.name = name;
        this.data = data;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void init() {

    }

    @Override
    public boolean contains(String player) {
        return data.contains(player);
    }

    @Override
    public List<String> getPlayers() {
        return data.getPlayers();
    }

    @Override
    public void addPlayer(String player) throws PlayerAlreadyExistsException {
        data.addPlayer(name, player);
    }

    @Override
    public void removePlayer(String player) throws PlayerNotFoundException {
        data.removePlayer(name, player);
    }

    @Override
    public void saveModifiedBuffer() {
        data.saveModifiedBuffer();
    }

    @Override
    public void deleteWhitelist() {
        data.removeStubWhitelist(name);
    }
}
