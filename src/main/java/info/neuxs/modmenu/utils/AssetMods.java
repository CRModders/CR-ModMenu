package info.neuxs.modmenu.utils;

public class AssetMods {
    private static String Name;
    private static String Authors;
    private static String Version;
    private static String Description;

    public static String getName() {
        return Name;
    }

    public static void setName(String name) {
        Name = name;
    }

    public static String getAuthors() {
        return Authors;
    }

    public static void setAuthors(String authors) {
        Authors = authors;
    }

    public static String getVersion() {
        return Version;
    }

    public static void setVersion(String version) {
        Version = version;
    }

    public static String getDescription() {
        return Description;
    }

    public static void setDescription(String description) {
        Description = description;
    }
}
