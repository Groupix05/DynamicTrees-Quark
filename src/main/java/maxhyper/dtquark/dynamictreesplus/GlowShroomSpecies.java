package maxhyper.dtquark.dynamictreesplus;

import com.ferreusveritas.dynamictrees.DynamicTrees;
import com.ferreusveritas.dynamictrees.api.registry.RegistryHandler;
import com.ferreusveritas.dynamictrees.api.registry.TypedRegistry;
import com.ferreusveritas.dynamictrees.block.DynamicSaplingBlock;
import com.ferreusveritas.dynamictrees.tree.family.Family;
import com.ferreusveritas.dynamictrees.tree.species.Species;
import com.ferreusveritas.dynamictrees.util.CommonVoxelShapes;
import com.ferreusveritas.dynamictreesplus.block.mushroom.CapProperties;
import com.ferreusveritas.dynamictreesplus.tree.HugeMushroomSpecies;
import maxhyper.dtquark.DynamicTreesQuark;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;

public class GlowShroomSpecies extends HugeMushroomSpecies {

    public static final TypedRegistry.EntryType<Species> TYPE = createDefaultMushroomType(GlowShroomSpecies::new);

    public GlowShroomSpecies(ResourceLocation name, Family family, CapProperties capProperties) {
        super(name, family, capProperties);
    }

    public Species generateSapling() {
        return !this.shouldGenerateSapling() || this.saplingBlock != null ? this :
                this.setSapling(RegistryHandler.addBlock(this.getSaplingRegName(), () -> new DynamicSaplingBlock(this){
                    @Override
                    public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
                        return 10;
                    }
                }));
    }

    @Override
    public ResourceLocation getSaplingSmartModelLocation() {
        return DynamicTreesQuark.location("block/glow_shroom");
    }

}
