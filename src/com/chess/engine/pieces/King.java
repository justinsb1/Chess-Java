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

public class King extends Piece {

    private static final int[] CANDIDATE_MOVE_COORDINATES = { -9, -8, -7, 1, 1, 7, 8, 9 };


    King(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();

        for(final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATES) {
            final int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;

            if(isFirstColumnExclusion(this.piecePosition, currentCandidateOffset) ||
                    isEightColumnExclusion(this.piecePosition, currentCandidateOffset)) {
                        continue;
            }

            if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                    if(!candidateDestinationTile.isTileOccupied()) {
                        legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
                    } else {
                        final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                        final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
                        if(this.pieceAlliance != pieceAlliance) {
                            legalMoves.add(new Move.AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
                        }
                    }
            }

        }

        return ImmutableList.copyOf(legalMoves);
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
        // all invalid moves where the Move algorithm breaks down if the Knight is in the first column
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -9 || candidateOffset == -1 ||
                candidateOffset == 7);
    }

    private static boolean isEightColumnExclusion(final int currentPosition, final int candidateOffset) {
        // all moves where the Move algorithm breaks down if the Knight is in the second column
        return BoardUtils.SECOND_COLUMN[currentPosition] && (candidateOffset == -7 || candidateOffset == 1 ||
                candidateOffset == 9);
    }
}
