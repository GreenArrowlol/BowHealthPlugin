package xyz.arrowtan.bowhealthplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class BowHealthPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if (event.getEntity().getShooter() instanceof Player && event.getEntity() instanceof org.bukkit.entity.Arrow) {
            Player shooter = (Player) event.getEntity().getShooter();
            Entity hitEntity = event.getHitEntity();

            if (hitEntity instanceof Player) {
                Player victim = (Player) hitEntity;
                double health = victim.getHealth() / 2.0;
                long roundedHealth = Math.round(health);
                shooter.sendMessage(ChatColor.YELLOW + "The health of " + ChatColor.GREEN + victim.getName() + ChatColor.YELLOW +" is " + ChatColor.RED + roundedHealth);
            }
        }
    }
}
