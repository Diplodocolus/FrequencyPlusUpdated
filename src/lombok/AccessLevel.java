package lombok;

public enum AccessLevel {
    PUBLIC, MODULE, PROTECTED, PACKAGE, PRIVATE,
    /** Represents not generating anything or the complete lack of a method. */
    NONE;
}
