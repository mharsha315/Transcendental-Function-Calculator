package com.example.f5;

/**
 * Version class to hold the semantic versioning information.
 */
public final class Version {
    /** The major version number. */
    private static final int MAJOR = 1;
    /** The minor version number. */
    private static final int MINOR = 1;
    /** The patch version number. */
    private static final int PATCH = 3;

    /**
     * Private constructor to prevent instantiation.
     */
    private Version() {
        // This constructor is intentionally empty. Nothing special is needed here.
    }

    /**
     * Returns the full version string.
     * @return The version string.
     */
    public static String getVersion() {
        return MAJOR + "." + MINOR + "." + PATCH;
    }
}
