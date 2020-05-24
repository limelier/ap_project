create type color as enum ('WHITE', 'BLACK');
create type rank as enum ('PAWN', 'ROOK', 'KNIGHT', 'BISHOP', 'QUEEN', 'KING');

create table board
(
    id          serial primary key,
    name        text,
    description text
);

create table piece
(
    board_id int references board,
    pos     smallint,
    color   color,
    rank    rank,
    primary key (board_id, pos)
);