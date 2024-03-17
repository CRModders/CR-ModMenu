package info.neuxs.modmenu.utils;

import java.util.ArrayList;
import java.util.List;

public class ModGrabber {
    protected List<String> allMods = new ArrayList<>();

    public static void getAllMods() {

    }

    public static int getModCount(boolean filteredList) {
        if (filteredList) return 2+AssetModGrabber.getModCount(true)+FabricModGrabber.getModCount(true);
        else return 2+AssetModGrabber.getModCount(false)+FabricModGrabber.getModCount(false);
    }

    public static String getName(int mod) {

    }
}
