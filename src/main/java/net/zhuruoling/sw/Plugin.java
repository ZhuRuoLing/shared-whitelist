package net.zhuruoling.sw;

import net.zhuruoling.omms.central.plugin.PluginMain;
import net.zhuruoling.omms.central.plugin.callback.WhitelistLoadCallback;

public class Plugin extends PluginMain {
    @Override
    public void onInitialize() {
        Data.instace.load();
        WhitelistLoadCallback.INSTANCE.register(whitelistManager -> {
            Data.instace.getWhitelistDatas().forEach(whitelistData -> {
                whitelistData.getNames().forEach(name -> {
                    whitelistManager.addWhitelist(new SharedWhitelist(name, whitelistData));
                });
            });
        });
    }
}
