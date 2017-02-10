-- Delete the tables if they exist.
-- Disable foreign key checks, so the tables can
-- be dropped in arbitrary order.
PRAGMA foreign_keys=OFF;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS reservations;
DROP TABLE IF EXISTS movies;
DROP TABLE IF EXISTS theaters;
DROP TABLE IF EXISTS movie_performances;
DROP TABLE IF EXISTS userhasreservations;
DROP TABLE IF EXISTS movieplaysin;
DROP TABLE IF EXISTS resforperformance;
DROP TABLE IF EXISTS showsin;

-- Add more tables when created
PRAGMA foreign_keys=ON;

-- Create the tables.
CREATE TABLE users (
  user_name     VARCHAR(32),
  name          VARCHAR(32),
  address       VARCHAR(32),
  phone_number  INT,
  PRIMARY KEY (user_name)
);

CREATE TABLE movie_performances(
  movie_name  VARCHAR(32),
  date            DATE,
  PRIMARY KEY (movie_name, date)
);

CREATE TABLE theaters (
  theater_name  VARCHAR(32),
  nbr_of_seats  INT,
  PRIMARY KEY (theater_name)
);

CREATE TABLE reservations(
  reservation_id  INTEGER,
  user_name       VARCHAR(32),
  movie_name      VARCHAR(32),
  date            DATE,
  PRIMARY KEY (reservation_id),
  FOREIGN KEY (user_name) REFERENCES users(user_name),
  FOREIGN KEY (movie_name,date) REFERENCES movie_performances(movie_name, date)
);

CREATE TABLE showsin(
  movie_name    VARCHAR(32),
  date          DATE,
  theater_name  VARCHAR(32),
  PRIMARY KEY (movie_name, date),
  FOREIGN KEY (movie_name, date) REFERENCES movie_performances(movie_name, date),
  FOREIGN KEY (theater_name) REFERENCES  theaters(theater_name)
);


INSERT INTO users(user_name, name, address, phone_number) VALUES ('Svenskjefel', 'Oscar', 'Kämners', 0230203);
INSERT INTO users(user_name, name, address, phone_number) VALUES ('Tjoflöjt', 'Fille', 'Oslo', 937493930);

INSERT INTO theaters(theater_name, nbr_of_seats) VALUES ('Stora Salongen', 200);
INSERT INTO theaters(theater_name, nbr_of_seats) VALUES ('Lilla Salongen', 50);

INSERT INTO movie_performances(movie_name, date)  VALUES ('Star wars', '02-02-2016');
INSERT INTO movie_performances(movie_name, date)  VALUES ('Star wars', '12-02-2016');
INSERT INTO movie_performances(movie_name, date)  VALUES ('Star wars', '22-02-2016');


INSERT INTO movie_performances(movie_name, date)  VALUES ('Trainspotting', '01-02-2016');
INSERT INTO movie_performances(movie_name, date)  VALUES ('Trainspotting', '11-02-2016');
INSERT INTO movie_performances(movie_name, date)  VALUES ('Trainspotting', '21-02-2016');

INSERT INTO movie_performances(movie_name, date)  VALUES ('28 Days later', '03-02-2016');
INSERT INTO movie_performances(movie_name, date)  VALUES ('28 Days later', '13-02-2016');
INSERT INTO movie_performances(movie_name, date)  VALUES ('28 Days later', '23-02-2016');


INSERT INTO reservations(user_name, movie_name, date) VALUES ('Svenskjefel', 'Star wars', '02-02-2016');
INSERT INTO reservations(user_name, movie_name, date) VALUES ('Svenskjefel', 'Star wars', '02-02-2016');
INSERT INTO reservations(user_name, movie_name, date) VALUES ('Svenskjefel', 'Star wars', '02-02-2016');
INSERT INTO reservations(user_name, movie_name, date) VALUES ('Svenskjefel', 'Star wars', '02-02-2016');
INSERT INTO reservations(user_name, movie_name, date) VALUES ('Svenskjefel', 'Star wars', '02-02-2016');
INSERT INTO reservations(user_name, movie_name, date) VALUES ('Svenskjefel', 'Star wars', '02-02-2016');

INSERT INTO showsin(movie_name, date, theater_name) VALUES('Star wars', '02-02-2016', 'Stora Salongen');
INSERT INTO showsin(movie_name, date, theater_name) VALUES('Star wars', '12-02-2016', 'Lilla Salongen');
INSERT INTO showsin(movie_name, date, theater_name) VALUES('Star wars', '22-02-2016', 'Stora Salongen');

INSERT INTO showsin(movie_name, date, theater_name) VALUES('Trainspotting', '01-02-2016', 'Lilla Salongen');
INSERT INTO showsin(movie_name, date, theater_name) VALUES('Trainspotting', '11-02-2016', 'Lilla Salongen');
INSERT INTO showsin(movie_name, date, theater_name) VALUES('Trainspotting', '21-02-2016', 'Lilla Salongen');


INSERT INTO showsin(movie_name, date, theater_name) VALUES('28 Days later', '03-02-2016', 'Lilla Salongen');
INSERT INTO showsin(movie_name, date, theater_name) VALUES('28 Days later', '13-02-2016', 'Stora Salongen');
INSERT INTO showsin(movie_name, date, theater_name) VALUES('28 Days later', '23-02-2016', 'Lilla Salongen');

