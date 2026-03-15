package xyz.arrowtan.bowhealthplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Locale;

public class BowHealthPlugin extends JavaPlugin implements Listener {

    private String messageFormat;
    private int decimalPlaces;
    private boolean useHearts;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        loadConfiguration();
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }

    private void loadConfiguration() {
        messageFormat = getConfig().getString("message-format", "&eThe health of &a{player}&e is &c{health}");
        decimalPlaces = Math.max(0, getConfig().getInt("health-decimal-places", 0));
        useHearts = getConfig().getBoolean("show-health-in-hearts", true);
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if (!(event.getEntity() instanceof Arrow) || !(event.getEntity().getShooter() instanceof Player)) {
            return;
        }

        Player shooter = (Player) event.getEntity().getShooter();
        Entity hitEntity = event.getHitEntity();

        if (!(hitEntity instanceof Player)) {
            return;
        }

        Player victim = (Player) hitEntity;
        double health = victim.getHealth();
        if (useHearts) {
            health /= 2.0;
        }

        String healthText = formatNumber(health, decimalPlaces);
        String rawMessage = messageFormat
                .replace("{player}", victim.getName())
                .replace("{health}", healthText);

        shooter.sendMessage(ChatColor.translateAlternateColorCodes('&', rawMessage));
    }

    private String formatNumber(double value, int places) {
        return String.format(Locale.US, "%1$." + places + "f", value);
    }
}
