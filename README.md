# BowHealthPlugin

A Spigot plugin that tells a player the target player's health when they hit them with an arrow.

## Configuration

After the plugin runs once, edit `plugins/BowHealthPlugin/config.yml`:

```yaml
message-format: "&eThe health of &a{player}&e is &c{health}"
health-decimal-places: 0
show-health-in-hearts: true
```

### Options
- `message-format`: Supports color codes (`&`) and placeholders `{player}` and `{health}`.
- `health-decimal-places`: Number of decimal places to display for health.
- `show-health-in-hearts`: If `true`, shows hearts; if `false`, shows raw health points.
