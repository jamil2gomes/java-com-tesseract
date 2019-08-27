create database portaria;



use portaria;

create table veiculo(
id int not null primary key auto_increment,
placa varchar(10)
);


insert into veiculo (placa) values("ABC1234");