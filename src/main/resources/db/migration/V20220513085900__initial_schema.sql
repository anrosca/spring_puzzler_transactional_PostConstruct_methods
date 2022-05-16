create table actors
(
    id   varchar(255) not null,
    name varchar(255),
    primary key (id)
);

create table movies
(
    id   varchar(255) not null,
    name varchar(255),
    primary key (id)
);

insert into movies (id, name) values('aa3e4567-e89b-12d3-b457-5267141750aa', 'Pulp Fiction');
