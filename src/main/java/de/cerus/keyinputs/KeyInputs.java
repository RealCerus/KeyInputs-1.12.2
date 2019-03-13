package de.cerus.keyinputs;

import de.cerus.keyinputs.listeners.KeyInputListener;
import net.labymod.api.LabyModAddon;
import net.labymod.settings.elements.BooleanElement;
import net.labymod.settings.elements.ControlElement;
import net.labymod.settings.elements.SettingsElement;
import net.labymod.utils.Material;

import java.util.List;

public class KeyInputs extends LabyModAddon {
    private boolean enabled;

    @Override
    public void onEnable() {
        getApi().registerForgeListener(new KeyInputListener(enabled));
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void loadConfig() {
        enabled = !getConfig().has("enabled") || getConfig().get("enabled").getAsBoolean();
    }

    @Override
    protected void fillSettings(List<SettingsElement> list) {
        list.add(new BooleanElement("Enabled", new ControlElement.IconData(Material.LEVER), enabled -> {
            if (getConfig().has("enabled"))
                getConfig().remove("enabled");
            getConfig().addProperty("enabled", enabled);
            saveConfig();
        }, this.enabled));
    }
}
