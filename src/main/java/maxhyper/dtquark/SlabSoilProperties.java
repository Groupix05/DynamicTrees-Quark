package maxhyper.dtquark;

import com.ferreusveritas.dynamictrees.api.registry.TypedRegistry;
import com.ferreusveritas.dynamictrees.blocks.rootyblocks.SoilProperties;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.ResourceLocation;

public class SlabSoilProperties extends SoilProperties {

    public static final TypedRegistry.EntryType<SoilProperties> TYPE = TypedRegistry.newType(SlabSoilProperties::new);

    public SlabSoilProperties(final ResourceLocation registryName) {
        super(registryName);
    }

    @Override
    public boolean isValidState(BlockState primitiveSoilState) {
        if (primitiveSoilState.hasProperty(SlabBlock.TYPE)){
            return primitiveSoilState.getValue(SlabBlock.TYPE) == SlabType.TOP;
        }
        return super.isValidState(primitiveSoilState);
    }

//    @Override
//    public BlockState getSoilState(BlockState primitiveSoilState, int fertility, boolean requireTileEntity) {
//        BlockState state = super.getSoilState(primitiveSoilState, fertility, requireTileEntity);
//        if (primitiveSoilState.hasProperty(SlabBlock.TYPE)){
//            return primitiveSoilState.getValue(SlabBlock.TYPE) == SlabType.TOP;
//        }
//        return state;
//    }

    @Override
    public BlockState getPrimitiveSoilState(BlockState currentSoilState) {
        BlockState primitiveSoilState = super.getPrimitiveSoilState(currentSoilState);
        if (primitiveSoilState.hasProperty(SlabBlock.TYPE)){
            return primitiveSoilState.setValue(SlabBlock.TYPE, SlabType.TOP);
        }
        return primitiveSoilState;
    }
}