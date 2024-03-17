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
    File modsFolder = FabricLoader.getInstance().getGameDir().resolve("mods").toFile();
    static List<ModContainer> allMods = new ArrayList<>();
    static List<ModContainer> filteredMods = new ArrayList<>();

    private static void getAllMods() {
        allMods.addAll(FabricLoader.getInstance().getAllMods());
    }

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

    public static File getModDir() {
        try {
            ProtectionDomain domain = Client.class.getProtectionDomain();
            CodeSource source = domain.getCodeSource();
            URL location = source.getLocation();
            URI uri = location.toURI();
            String path = uri.getPath();
            String decodedPath = URLDecoder.decode(path, "UTF-8");

            File jarFile = new File(decodedPath);
            String rootDirectory = jarFile.getParentFile().getAbsolutePath();

            return new File(rootDirectory);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new File("Exception");
        }
    }

    public static int getModCount(boolean filteredList) {
        if (filteredList) return filteredMods.size();
        else return allMods.size();
    }

    public static String grabName(int mod, boolean filteredList) {
        if (filteredList) return filteredMods.get(mod).getMetadata().getName();
        else return allMods.get(mod).getMetadata().getName();
    }

    public static String grabAuthors(int mod) {
        Collection<Person> originalAuthors = filteredMods.get(mod).getMetadata().getAuthors();
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
