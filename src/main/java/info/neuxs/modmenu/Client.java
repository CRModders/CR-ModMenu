package info.neuxs.modmenu;

import net.fabricmc.api.ModInitializer;

import java.util.logging.Logger;

import static info.neuxs.modmenu.utils.FabricModGrabber.filterModList;

public class Client implements ModInitializer {
    public static final String MOD_ID = "modmenu";
    public static final Logger LOGGER = Logger.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing ModMenu");
        filterModList();
        LOGGER.info("Finished loading ModMenu");
    }
}
