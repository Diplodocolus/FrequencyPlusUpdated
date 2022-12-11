package xyz.elevated.frequency.data;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import xyz.elevated.frequency.util.NmsUtil;

@Getter @Setter
public final class BlockPosition {
    private int x, y, z;

    public BlockPosition(final int x, final int y, final int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Block getBlock(final World world) {
        return NmsUtil.INSTANCE.getBlock(new Location(world, x, y, z));
    }
}
