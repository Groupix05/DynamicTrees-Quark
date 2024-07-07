package maxhyper.dtquark.mushroom;

import com.ferreusveritas.dynamictrees.api.registry.RegistryHandler;
import com.ferreusveritas.dynamictrees.api.registry.TypedRegistry;
import com.ferreusveritas.dynamictrees.block.DynamicSaplingBlock;
import com.ferreusveritas.dynamictrees.tree.family.Family;
import com.ferreusveritas.dynamictrees.tree.species.Species;
import com.ferreusveritas.dynamictreesplus.block.mushroom.CapProperties;
import com.ferreusveritas.dynamictreesplus.tree.HugeMushroomSpecies;
import maxhyper.dtquark.DynamicTreesQuark;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

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
                    @Override
                    public void animateTick(@NotNull BlockState stateIn, @NotNull Level worldIn, @NotNull BlockPos pos, @NotNull RandomSource rand) {
                        super.animateTick(stateIn, worldIn, pos, rand);
                        if (rand.nextInt(12) == 0 && worldIn.getBlockState(pos.above()).isAir()) {
                            worldIn.addParticle(ParticleTypes.END_ROD, (double)pos.getX() + 0.4 + rand.nextDouble() * 0.2, (double)pos.getY() + 0.5 + rand.nextDouble() * 0.1, (double)pos.getZ() + 0.4 + rand.nextDouble() * 0.2, (Math.random() - 0.5) * 0.04, (1.0 + Math.random()) * 0.02, (Math.random() - 0.5) * 0.04);
                        }
                    }
                }));
    }

    @Override
    public ResourceLocation getSaplingSmartModelLocation() {
        return DynamicTreesQuark.location("block/glow_shroom");
    }

}
