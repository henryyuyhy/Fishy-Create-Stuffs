package com.yu.fishycreatestuffs.content.trainstair;

import com.simibubi.create.content.equipment.wrench.IWrenchable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TrainStairBlock extends HorizontalDirectionalBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;


    public TrainStairBlock(Properties petai) {
        super(petai);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        super.createBlockStateDefinition(builder);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        switch (state.getValue(FACING)) {
            case NORTH -> {
                return Block.box(0, 0, 0, 16, 16, 3);
            }
            case EAST -> {
                return Block.box(13, 0, 0, 16, 16, 16);
            }
            case SOUTH -> {
                return Block.box(0, 0, 13, 16, 16, 16);
            }
            case WEST -> {
                return Block.box(0, 0, 0, 3, 16, 16);
            }
            default -> {
                return Block.box(0, 0, 0, 16, 16, 16);
            }
        }
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        switch (state.getValue(FACING)) {
            case NORTH -> {
                return Block.box(0, 0, 0, 16, 1, 3);
            }
            case EAST -> {
                return Block.box(13, 0, 0, 16, 1, 16);
            }
            case SOUTH -> {
                return Block.box(0, 0, 13, 16, 1, 16);
            }
            case WEST -> {
                return Block.box(0, 0, 0, 3, 1, 16);
            }
            default -> {
                return Block.box(0, 0, 0, 16, 16, 16);
            }
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        if (context.getPlayer() != null) {
            Direction[] directions = Direction.orderedByNearest(context.getPlayer());
            Direction horizonalDirection;
            if (directions[0].getAxis() == Direction.Axis.Y) {
                horizonalDirection = directions[1];
            }
            else {
                horizonalDirection = directions[0];
            }
            if (context.getPlayer().isShiftKeyDown()) {
                horizonalDirection = horizonalDirection.getOpposite();
            }
            return defaultBlockState().setValue(FACING, horizonalDirection);
        }
        return defaultBlockState();
    }

}
