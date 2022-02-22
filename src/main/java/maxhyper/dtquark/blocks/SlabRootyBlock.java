package maxhyper.dtquark.blocks;

import com.ferreusveritas.dynamictrees.blocks.rootyblocks.RootyBlock;
import com.ferreusveritas.dynamictrees.blocks.rootyblocks.SoilProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.SlabType;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

public class SlabRootyBlock extends RootyBlock implements IWaterLoggable {

    public enum SlabTypeNoBottom implements IStringSerializable {
        TOP("top"),
        DOUBLE("double");
        private final String name;
        SlabTypeNoBottom(String name) {
            this.name = name;
        }
        public String toString() {
            return this.name;
        }
        public String getSerializedName() {
            return this.name;
        }
    }
    public static final EnumProperty<SlabTypeNoBottom> TYPE = EnumProperty.create("type", SlabTypeNoBottom.class);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public SlabRootyBlock(SoilProperties properties, Properties blockProperties) {
        super(properties, blockProperties);

        registerDefaultState(defaultBlockState().setValue(TYPE, SlabTypeNoBottom.TOP).setValue(WATERLOGGED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(TYPE).add(WATERLOGGED);
    }

    public FluidState getFluidState(BlockState blockState) {
        return blockState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(blockState);
    }

    public boolean placeLiquid(IWorld world, BlockPos pos, BlockState blockState, FluidState fluidState) {
        return blockState.getValue(TYPE) != SlabTypeNoBottom.DOUBLE && IWaterLoggable.super.placeLiquid(world, pos, blockState, fluidState);
    }

    public boolean canPlaceLiquid(IBlockReader world, BlockPos pos, BlockState blockState, Fluid fluid) {
        return blockState.getValue(TYPE) != SlabTypeNoBottom.DOUBLE && IWaterLoggable.super.canPlaceLiquid(world, pos, blockState, fluid);
    }

    public BlockState updateShape(BlockState state, Direction from, BlockState fromState, IWorld world, BlockPos pos, BlockPos fromPos) {
        if (state.getValue(WATERLOGGED)) {
            world.getLiquidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }

        return super.updateShape(state, from, fromState, world, pos, fromPos);
    }

    public boolean isPathfindable(BlockState state, IBlockReader world, BlockPos pos, PathType pathType) {
        if (pathType == PathType.WATER)
            return world.getFluidState(pos).is(FluidTags.WATER);
        return false;
    }

}
