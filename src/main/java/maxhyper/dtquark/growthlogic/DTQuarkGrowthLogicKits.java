package maxhyper.dtquark.growthlogic;

import com.ferreusveritas.dynamictrees.api.registry.Registry;
import com.ferreusveritas.dynamictrees.growthlogic.GrowthLogicKit;
import maxhyper.dtquark.DynamicTreesQuark;

public class DTQuarkGrowthLogicKits {

    public static final GrowthLogicKit ANCIENT = new AncientLogic(DynamicTreesQuark.location("ancient"));

    public static void register(Registry<GrowthLogicKit> registry) {
        registry.register(ANCIENT);
    }

}
