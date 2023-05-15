CREATE TABLE IF NOT EXISTS player (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(255) NOT NULL,
                    password VARCHAR(255) NOT NULL,
                    painted_pixels INT NOT NULL
                        );
insert into player (name, password, painted_pixels) values('joao', 'joao123', 3);
insert into player (name, password, painted_pixels) values('maria', 'maria123', 2);
