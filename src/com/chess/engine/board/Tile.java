// moved this class to package
package com.chess.engine.board;

// import piece class
import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;
import java.util.HashMap;
import java.util.Map;


// abstract - you can't instantiate this class
public abstract class Tile {

    //member field
    protected final int tileCoordinate;  // (Immutable) - once set at construction, it can not be set again.

    private static final Map<Integer, EmptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTiles();

    // this method will
    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {

        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();

        for(int i = 0; i < 64; i++) {
            emptyTileMap.put(i, new EmptyTile(i));
        }
        // ImmutableMap is apart of Guava library (3rd Party Library)
        return ImmutableMap.copyOf(emptyTileMap); // After this Map is made, nobody can change it by making it Immutable
    }

    // Only method that someone else can use. They can create a new occupied tile or empty tile
    public static Tile createTile(final int tileCoordinate, final Piece piece) {
        return piece != null ? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILES_CACHE.get(tileCoordinate);
    }

    //Constructor for Tile class
    private Tile(int tileCoordinate) {
        this.tileCoordinate = tileCoordinate;
    }

    //Methods
    public abstract boolean isTileOccupied(); // abstract because it is not defined here in this class, defined in a subclass

    public abstract Piece getPiece(); // get the piece off the occupied tile

    // Subclass to represent either an empty tile or occupied tile
    public static final class EmptyTile extends Tile {
        EmptyTile(final int coordinate) {
            super(coordinate);
        }
            @Override
            public boolean isTileOccupied() {
                // there is no piece on the tile
                return false;
            }

            @Override
            public Piece getPiece() {
                // no piece to return on an empty tile
                return null;
        }
    }

    //define occupied tile
    public static final class OccupiedTile extends Tile {

        private final Piece pieceOnTile; // Can be mutated so made Private - no way to reference this variable outside without calling getPiece()

        OccupiedTile(int tileCoordinate, Piece pieceOnTile) {
            // establishes the tile coordinate
            super(tileCoordinate);
            this.pieceOnTile = pieceOnTile;
        }

        @Override
        public boolean isTileOccupied() {
            return true;
        }

        @Override
        public Piece getPiece() {
            return this.pieceOnTile;
        }
    }
}
