package maxhyper.dtquark.blocks;

import com.ferreusveritas.dynamictrees.api.registry.TypedRegistry;
import com.ferreusveritas.dynamictrees.blocks.rootyblocks.RootyBlock;
import com.ferreusveritas.dynamictrees.blocks.rootyblocks.SoilProperties;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.properties.Half;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import vazkii.quark.content.building.block.VerticalSlabBlock;

public class SlabSoilProperties extends SoilProperties {

    public static final TypedRegistry.EntryType<SoilProperties> TYPE_HOR = TypedRegistry.newType((r)->new SlabSoilProperties(r, BlockType.HORIZONTAL));
    public static final TypedRegistry.EntryType<SoilProperties> TYPE_VER = TypedRegistry.newType((r)->new SlabSoilProperties(r, BlockType.VERTICAL));
    public static final TypedRegistry.EntryType<SoilProperties> TYPE_STA = TypedRegistry.newType((r)->new SlabSoilProperties(r, BlockType.STAIRS));

    private enum BlockType {
        VERTICAL,
        HORIZONTAL,
        STAIRS
    }
    private final BlockType slabType;
    public SlabSoilProperties(final ResourceLocation registryName, BlockType type) {
        super(registryName);
        this.slabType = type;
    }

    protected RootyBlock createBlock(AbstractBlock.Properties blockProperties) {
        switch (slabType){
            case HORIZONTAL:
                return new SlabRootyBlock(this, blockProperties);
            case STAIRS:
                return new StairsRootyBlock(this, blockProperties);
            default:
            case VERTICAL:
                return new RootyBlock(this, blockProperties);
        }
    }

    @Override
    public boolean isValidState(BlockState primitiveSoilState) {
        switch (slabType){
            default:
            case HORIZONTAL:
                if (primitiveSoilState.hasProperty(SlabBlock.TYPE)){
                    return primitiveSoilState.getValue(SlabBlock.TYPE) == SlabType.TOP ||
                            primitiveSoilState.getValue(SlabBlock.TYPE) == SlabType.DOUBLE;
                }
                break;
            case VERTICAL:
                if (primitiveSoilState.hasProperty(VerticalSlabBlock.TYPE)){
                    return primitiveSoilState.getValue(VerticalSlabBlock.TYPE) == VerticalSlabBlock.VerticalSlabType.DOUBLE;
                }
                break;
            case STAIRS:
                if (primitiveSoilState.hasProperty(StairsBlock.HALF)){
                    return primitiveSoilState.getValue(StairsBlock.HALF) == Half.TOP;
                }
        }
        return super.isValidState(primitiveSoilState);
    }

    @Override
    public BlockState getSoilState(BlockState primitiveSoilState, int fertility, boolean requireTileEntity) {
        BlockState state = super.getSoilState(primitiveSoilState, fertility, requireTileEntity);
        switch (slabType){
            case HORIZONTAL:
                return state.setValue(SlabRootyBlock.TYPE, SlabRootyBlock.SlabTypeNoBottom.valueOf(primitiveSoilState.getValue(SlabBlock.TYPE).toString().toUpperCase())).setValue(SlabRootyBlock.WATERLOGGED, primitiveSoilState.getValue(SlabBlock.WATERLOGGED));
            case STAIRS:
                return state.setValue(StairsRootyBlock.FACING, primitiveSoilState.getValue(StairsBlock.FACING)).setValue(StairsRootyBlock.WATERLOGGED, primitiveSoilState.getValue(StairsBlock.WATERLOGGED));
            default:
            case VERTICAL:
                return state;
        }
    }

    @Override
    public BlockState getPrimitiveSoilState(BlockState currentSoilState) {
        BlockState primitiveSoilState = super.getPrimitiveSoilState(currentSoilState);
        switch (slabType){
            default:
            case HORIZONTAL:
                if (primitiveSoilState.hasProperty(SlabBlock.TYPE)){
                    return primitiveSoilState.setValue(SlabBlock.TYPE, SlabType.valueOf(currentSoilState.getValue(SlabRootyBlock.TYPE).toString().toUpperCase())).setValue(SlabBlock.WATERLOGGED, currentSoilState.getValue(SlabRootyBlock.WATERLOGGED));
                }
                break;
            case VERTICAL:
                if (primitiveSoilState.hasProperty(VerticalSlabBlock.TYPE)){
                    return primitiveSoilState.setValue(VerticalSlabBlock.TYPE, VerticalSlabBlock.VerticalSlabType.DOUBLE);
                }
                break;
            case STAIRS:
                if (primitiveSoilState.hasProperty(StairsBlock.HALF) && primitiveSoilState.hasProperty(StairsBlock.FACING)){
                    return primitiveSoilState.setValue(StairsBlock.HALF, Half.TOP).setValue(StairsBlock.FACING, currentSoilState.getValue(StairsRootyBlock.FACING)).setValue(StairsBlock.WATERLOGGED, currentSoilState.getValue(StairsRootyBlock.WATERLOGGED));
                }
        }

        return primitiveSoilState;
    }
}