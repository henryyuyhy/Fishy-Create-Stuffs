package com.yu.fishycreatestuffs.content.throughshaftgearbox;


import com.simibubi.create.content.kinetics.transmission.SplitShaftBlockEntity;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

@MethodsReturnNonnullByDefault
public class ThroughShaftGearboxEntity extends SplitShaftBlockEntity {

    public ThroughShaftGearboxEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public float getRotationSpeedModifier(Direction face) {
        if (!hasSource()) return 1;
        return getRotationSpeedModifier(getBlockState(), face, getSourceFacing());
    }

    public static float getRotationSpeedModifier(BlockState state, Direction face, Direction source) {
        float modifier = face.getAxisDirection() == source.getAxisDirection() ? 1 : -1;
        Direction direction = state.getValue(ThroughShaftGearboxBlock.FACING);
        if (direction == face) {
            modifier *= -1;
        }

        return modifier;
    }
}
