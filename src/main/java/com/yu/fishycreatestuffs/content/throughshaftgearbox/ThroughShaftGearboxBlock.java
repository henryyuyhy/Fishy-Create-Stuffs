package com.yu.fishycreatestuffs.content.throughshaftgearbox;

import com.simibubi.create.api.contraption.transformable.TransformableBlock;
import com.simibubi.create.content.contraptions.StructureTransform;
import com.simibubi.create.content.kinetics.base.KineticBlock;
import com.simibubi.create.foundation.block.IBE;

import com.yu.fishycreatestuffs.BlockEntityTypes;

import net.createmod.catnip.math.VoxelShaper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class ThroughShaftGearboxBlock extends KineticBlock implements IBE<ThroughShaftGearboxEntity>, TransformableBlock {

	public static final EnumProperty<Axis> AXIS = BlockStateProperties.AXIS;
	public static final DirectionProperty FACING = BlockStateProperties.FACING;
	// FACING blockstate
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

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		if (context.getPlayer() != null) {
			Direction[] directions = Direction.orderedByNearest(context.getPlayer());
			Direction horizontalDirection;
			if (directions[0].getAxis() == Axis.Y) {
				horizontalDirection = directions[1];
			} else {
				horizontalDirection = directions[0];
			}
			if (context.getPlayer()
					.isShiftKeyDown()) {
				horizontalDirection = horizontalDirection.getOpposite();
			}
			return defaultBlockState().setValue(FACING, horizontalDirection)
					.setValue(AXIS, Axis.Y);
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

	/**
	 * FACING decides which of the four shafts has its rotation inverted, so turning
	 * the block around its own axis changes the output of the gearbox even though the
	 * rotation axis itself stays put. Comparing FACING here as well makes Create
	 * re-propagate the kinetic network on every wrench turn, which is what the 1.20.1
	 * version achieved by force-breaking and re-placing the block.
	 */
	@Override
	protected boolean areStatesKineticallyEquivalent(BlockState oldState, BlockState newState) {
		if (oldState.getBlock() != newState.getBlock())
			return false;
		return getRotationAxis(newState) == getRotationAxis(oldState)
				&& oldState.getValue(FACING) == newState.getValue(FACING);
	}

	@Override
	public BlockState getRotatedBlockState(BlockState originalState, Direction targetedFace) {
		Axis rotateAxis = targetedFace.getAxis();
		return originalState
				.setValue(AXIS, VoxelShaper.axisAsFace(originalState.getValue(AXIS))
						.getClockWise(rotateAxis)
						.getAxis())
				.setValue(FACING, originalState.getValue(FACING)
						.getClockWise(rotateAxis));
	}

	@Override
	public BlockState transform(BlockState state, StructureTransform transform) {
		if (transform.mirror != null)
			state = mirror(state, transform.mirror);

		if (transform.rotation != Rotation.NONE) {
			state = state.setValue(FACING, transform.rotateFacing(state.getValue(FACING)))
					.setValue(AXIS, transform.rotateAxis(state.getValue(AXIS)));
		}
		return state;
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
