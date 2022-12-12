create table hibernate_sequence (
    next_val bigint
);

insert into hibernate_sequence values ( 1 );

create table color (
    id integer not null,
    coating_type varchar(255),
    manufact varchar(255),
    number_color integer,
    value double precision not null,
    primary key (id)
);

create table correct_img (
    defect_correction_id integer not null,
    image_address varchar(255)
);

create table defect (
    id integer not null,
    description varchar(255),
    product_id integer,
    primary key (id)
);

create table defect_img (
    defect_id integer not null,
    image_address varchar(255)
);

create table defect_correction (
    id integer not null,
    culprit varchar(255),
    message varchar(255),
    status integer,
    defect_id integer,
    primary key (id)
);


create table product (
    id integer not null,
    description varchar(255),
    filename varchar(255),
    name varchar(255),
    primary key (id)
);

create table user_role (
    user_id bigint not null,
    roles varchar(255)
);

create table usr (
    id bigint not null,
    active bit not null,
    password varchar(255),
    username varchar(255),
    primary key (id)
);

alter table correct_img
    add constraint correct_img_defect_correction_fk
    foreign key (defect_correction_id) references defect_correction (id);

alter table defect
    add constraint defect_product_fk
    foreign key (product_id) references product (id);

alter table defect_img
    add constraint defect_img_defect_fk
    foreign key (defect_id) references defect (id);

alter table defect_correction
    add constraint defect_correction_defect_fk
    foreign key (defect_id) references defect (id);

alter table user_role
    add constraint user_role_user_fk
    foreign key (user_id) references usr (id);