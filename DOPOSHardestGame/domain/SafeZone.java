package domain;

public class SafeZone extends Entity {
    private final SafeZoneType type;

    public SafeZone(Position position, Size size, SafeZoneType type) {
        super(position, size);
        this.type = type;
    }

    public SafeZoneType getType() {
        return type;
    }

    public boolean contains(Player player) {
        return collidesWith(player);
    }
}

