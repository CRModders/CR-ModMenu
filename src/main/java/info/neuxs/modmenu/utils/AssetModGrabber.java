package info.neuxs.modmenu.utils;

import net.fabricmc.loader.api.metadata.Person;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static info.neuxs.modmenu.utils.FabricModGrabber.getModDir;

public class AssetModGrabber {

    private static List<File> allMods = new ArrayList<>();
    public static List<File> filteredMods = new ArrayList<>();

    private static void getAllMods() {
        FilenameFilter jsonFilter = (dir, name) -> name.toLowerCase().endsWith(".json");
        File jsonDir = new File(getModDir() + "/assets/properties/");
        File[] jsonFiles = jsonDir.listFiles(jsonFilter);

        if (jsonFiles != null) {
            allMods.addAll(Arrays.asList(jsonFiles));
        } else {
            System.out.println("Directory not found or empty.");
        }
    }

    public static int getModCount(boolean filteredList) {
        if (filteredList) return filteredMods.size();
        else return allMods.size();
    }

    public static String grabName(int mod, boolean filteredList) {
        if (filteredList) return filteredMods.get(mod).getName();
        else return allMods.get(mod).getName();
    }

    public static String grabAuthors(int mod) {
        Collection<Person> originalAuthors = filteredMods.get(mod).getAuthors();
        List<Person> authors = new ArrayList<>(originalAuthors);

        StringBuilder authorsString = new StringBuilder();

        for (int i = 0; i < authors.size(); i++) {
            authorsString.append(authors.get(i).getName());
            if (i < authors.size() - 1) {
                authorsString.append(", ");
            }
        }

        return authorsString.toString();
    }


    public static String grabDescription(int mod) {
        return filteredMods.get(mod).getMetadata().getDescription();
    }

    public static String grabVersion(int mod) {
        return filteredMods.get(mod).getMetadata().getVersion().toString();
    }
}
