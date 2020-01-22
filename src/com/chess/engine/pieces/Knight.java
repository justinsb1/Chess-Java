package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Knight extends Piece {

    private final static int[] CANDIDATE_MOVE_COORDINATE = {-17, -15, -10, -6, 6, 10, 15, 17};

    Knight(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        // loop through candidate locations
        for(final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE) {

            final int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;

            if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {

                if(isFirstColumnExclusion(this.piecePosition, currentCandidateOffset) ||
                    isSecondColumnExclusion(this.piecePosition, currentCandidateOffset) ||
                        isSeventhColumnExclusion(this.piecePosition, currentCandidateOffset) ||
                            isEightColumnExclusion(this.piecePosition, currentCandidateOffset)) {
                    continue;
                }
                
                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);

                if(!candidateDestinationTile.isTileOccupied()) /* if tile isn't occupied */ {
                    legalMoves.add(new Move());
                }
                else {
                    final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                    final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();

                    if(this.pieceAlliance != pieceAlliance) /* piece is enemy */ {
                        legalMoves.add(new Move());
                    }
                }
            }
        }

        return ImmutableList.copyOf(legalMoves);
    }

    // edge cases
    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
        // all invalid moves where the Move algorithm breaks down if the Knight is in the first column
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -17 || candidateOffset == -10 ||
                candidateOffset == 6 || candidateOffset == 15);
    }

    private static boolean isSecondColumnExclusion(final int currentPosition, final int candidateOffset) {
        // all moves where the Move algorithm breaks down if the Knight is in the first column
        return BoardUtils.SECOND_COLUMN[currentPosition] && (candidateOffset == -10 || candidateOffset == 6);
    }

    private static boolean isSeventhColumnExclusion(final int currentPosition, final int candidateOffset) {
        // all moves where the Move algorithm breaks down if the Knight is in the first column
        return BoardUtils.SEVENTH_COLUMN[currentPosition] && (candidateOffset == -6 || candidateOffset == 10);
    }

    private static boolean isEightColumnExclusion(final int currentPosition, final int candidateOffset) {
        // all moves where the Move algorithm breaks down if the Knight is in the first column
        return BoardUtils.EIGHT_COLUMN[currentPosition] && (candidateOffset == -15 || candidateOffset == -6 ||
                candidateOffset == 10 || candidateOffset == 17);
    }

}
