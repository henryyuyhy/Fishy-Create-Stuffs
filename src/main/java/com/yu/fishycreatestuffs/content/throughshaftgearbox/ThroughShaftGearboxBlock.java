package com.yu.fishycreatestuffs.content.throughshaftgearbox;

import com.yu.fishycreatestuffs.BlockEntityTypes;
import com.simibubi.create.content.kinetics.base.GeneratingKineticBlockEntity;
import com.simibubi.create.content.kinetics.base.KineticBlock;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.foundation.block.IBE;
import com.simibubi.create.foundation.utility.VoxelShaper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import org.jetbrains.annotations.Nullable;
import net.fabricmc.fabric.api.block.BlockPickInteractionAware;

public class ThroughShaftGearboxBlock extends KineticBlock implements IBE<ThroughShaftGearboxEntity>, BlockPickInteractionAware  {

    public static final EnumProperty<Axis> AXIS = BlockStateProperties.AXIS;
    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    //FACING blockstate
    //       |
    //    --G|G--
    //       G
    //       |
    // I hope I explained it clearly

    public ThroughShaftGearboxBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(AXIS, Axis.Y)
                .setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(AXIS);
        super.createBlockStateDefinition(builder);
    }



    @SuppressWarnings("deprecation")
    @Override
    public @NotNull List<ItemStack> getDrops(BlockState state, @NotNull LootParams.Builder builder) {
        return super.getDrops(state, builder);
    }
	
	@Override
	public ItemStack getPickedStack(BlockState state, BlockGetter view, BlockPos pos, @Nullable Player player, @Nullable HitResult result) {
        return super.getCloneItemStack(view, pos, state);
	}

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        if (context.getPlayer() != null) {
            Direction[] directions = Direction.orderedByNearest(context.getPlayer());
            Direction horizonalDirection;
            if (directions[0].getAxis() == Axis.Y) {
                horizonalDirection = directions[1];
            }
            else {
                horizonalDirection = directions[0];
            }
            if (context.getPlayer().isShiftKeyDown()) {
                horizonalDirection = horizonalDirection.getOpposite();
            }
            return defaultBlockState().setValue(FACING, horizonalDirection).setValue(AXIS, Axis.Y);
        }
        return defaultBlockState();
    }

    @Override
    public boolean hasShaftTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
        return face.getAxis() != state.getValue(AXIS);
    }

    @Override
    public Axis getRotationAxis(BlockState state) {
        return state.getValue(AXIS);
    }

    @Override
    public InteractionResult onWrenched(BlockState state, UseOnContext context) {
        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState rotated = getRotatedBlockState(state, context.getClickedFace());
        if (!rotated.canSurvive(world, context.getClickedPos()))
            return InteractionResult.PASS;

        KineticBlockEntity.switchToBlockState(world, context.getClickedPos(), updateAfterWrenched(rotated, context));

        BlockEntity be = context.getLevel()
                .getBlockEntity(context.getClickedPos());
        if (be instanceof GeneratingKineticBlockEntity) {
            ((GeneratingKineticBlockEntity) be).reActivateSource = true;
        }
        world.removeBlock(pos, false);
        world.setBlockAndUpdate(pos, rotated);
        //shafts does not change rotating direction when the block is rotated, so I figured out the way of removing it and placing it again
        if (world.getBlockState(context.getClickedPos()) != state)
            playRotateSound(world, context.getClickedPos());

        return InteractionResult.SUCCESS;
    }

    @Override
    public BlockState getRotatedBlockState(BlockState originalState, Direction targetedFace) {
        Axis rotateAxis = targetedFace.getAxis();
        return originalState
                .setValue(AXIS, VoxelShaper.axisAsFace(originalState.getValue(AXIS)).getClockWise(rotateAxis).getAxis())
                .setValue(FACING, originalState.getValue(FACING).getClockWise(rotateAxis));
    }

    @Override
    public Class<ThroughShaftGearboxEntity> getBlockEntityClass() {
        return ThroughShaftGearboxEntity.class;
    }

    @Override
    public BlockEntityType<? extends ThroughShaftGearboxEntity> getBlockEntityType() {
        return BlockEntityTypes.THROUGH_SHAFT_GEARBOX.get();
    }
}
