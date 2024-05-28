-- init.sql

CREATE TABLE IF NOT EXISTS clan (
    id INT AUTO_INCREMENT PRIMARY KEY,
    average_battles INT NOT NULL,
    average_damage FLOAT NOT NULL,
    average_exp FLOAT NOT NULL,
    clan_name VARCHAR(255) NOT NULL,
    clan_rating FLOAT NOT NULL,
    total_members INT NOT NULL,
    win_rate FLOAT NOT NULL
);

CREATE TABLE IF NOT EXISTS player (
    id INT AUTO_INCREMENT PRIMARY KEY,
    average_damage FLOAT NOT NULL,
    average_exp FLOAT NOT NULL,
    nickname VARCHAR(255) NOT NULL,
    total_battles INT NOT NULL,
    win_rate FLOAT NOT NULL,
    clan_id INT,
    clan_name VARCHAR(255),
    FOREIGN KEY (clan_id) REFERENCES clan(id)
);

-- Insert initial data for clans
INSERT INTO clan (average_battles, average_damage, average_exp, clan_name, clan_rating, total_members, win_rate)
VALUES (500, 3000.5, 1500.5, 'Clan1', 2000, 100, 75.5);

INSERT INTO clan (average_battles, average_damage, average_exp, clan_name, clan_rating, total_members, win_rate)
VALUES (400, 2800.3, 1400.2, 'Clan2', 1950, 90, 74.3);

-- Insert initial data for players
INSERT INTO player (average_damage, average_exp, nickname, total_battles, win_rate, clan_id, clan_name)
VALUES (3500.5, 1600.5, 'Player1', 1000, 80.5, 1, 'Clan1');

INSERT INTO player (average_damage, average_exp, nickname, total_battles, win_rate, clan_id, clan_name)
VALUES (3400.3, 1550.2, 'Player2', 900, 79.3, 1, 'Clan1');

INSERT INTO player (average_damage, average_exp, nickname, total_battles, win_rate, clan_id, clan_name)
VALUES (3300.5, 1500.5, 'Player3', 800, 78.5, 2, 'Clan2');

INSERT INTO player (average_damage, average_exp, nickname, total_battles, win_rate, clan_id, clan_name)
VALUES (3200.3, 1450.2, 'Player4', 700, 77.3, 2, 'Clan2');
