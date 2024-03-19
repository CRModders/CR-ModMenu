package info.neuxs.modmenu.utils;

import info.neuxs.modmenu.Client;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.Person;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.*;

public class FabricModGrabber {
    private static final FabricLoader fabricInstance = FabricLoader.getInstance();
    File modsFolder = fabricInstance.getGameDir().resolve("mods").toFile();

    /*
    public static void filterModList() {
        getAllMods();

        for (ModContainer mod : allMods) {
            if (!(
                    Objects.equals(mod.getMetadata().getId(), "mixinextras") ||
                    Objects.equals(mod.getMetadata().getId(), "fabricloader") ||
                    Objects.equals(mod.getMetadata().getId(), "java")
            )) {
                filteredMods.add(mod);
                System.out.println("Added mod: " + mod + " | " + mod.getMetadata().getId());
            } else {
                System.out.println("Removing filtered mod: " + mod.getMetadata().getId());
            }
        }

        filteredMods.sort(Comparator.comparing(mod -> mod.getMetadata().getName()));
    }
    */

    public static File getModDir() {
        return fabricInstance.getGameDir().toFile();
    }

    public static int getModCount() {
        return fabricInstance.getAllMods().size();
    }

    public static String grabName(String mod) {
        return fabricInstance.getModContainer(mod).get().getMetadata().getName();
    }

    public static String grabAuthors(String mod) {
        Collection<Person> originalAuthors = fabricInstance.getModContainer(mod).get().getMetadata().getAuthors();
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


    public static String grabDescription(String mod) {
        return fabricInstance.getModContainer(mod).get().getMetadata().getDescription();
    }

    public static String grabVersion(String mod) {
        return fabricInstance.getModContainer(mod).get().getMetadata().getVersion().getFriendlyString();
    }
}
