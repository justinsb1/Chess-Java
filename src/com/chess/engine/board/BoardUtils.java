package com.chess.engine.board;

public class BoardUtils {

    public static final boolean[] FIRST_COLUMN = null;
    public static final boolean[] SECOND_COLUMN = null;
    public static final boolean[] SEVENTH_COLUMN = null;
    public static final boolean[] EIGHT_COLUMN = null;


    private BoardUtils() {
        throw new RuntimeException("You cannot instantiate me!");
    }


    public static boolean isValidTileCoordinate(int coordinate) {
        // checks to see if the tile is a valid tile and not out of bounds
        return coordinate >=0 && coordinate < 64;
    }
}
