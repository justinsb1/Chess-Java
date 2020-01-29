package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Move;
import com.chess.engine.board.Board;
import java.util.Collection;

public abstract class Piece {


    // Member fields - Every piece has a tile position
    protected final int piecePosition;
    protected final Alliance pieceAlliance;
    protected final boolean isFirstMove;

    Piece(final int piecePosition, final Alliance pieceAlliance ) {
        this.pieceAlliance = pieceAlliance;
        this.piecePosition = piecePosition;
        this.isFirstMove = false;
    }

    public Alliance getPieceAlliance() {
        return this.pieceAlliance;
    }

    public boolean isFirstMove() {
        return this.isFirstMove;
    }

    // to calculate legal modes
    public abstract Collection<Move> calculateLegalMoves(final Board board);
}
