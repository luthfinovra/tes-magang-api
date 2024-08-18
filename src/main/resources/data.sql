-- Drop tables if they exist
DROP TABLE IF EXISTS proyek_lokasi;
DROP TABLE IF EXISTS proyek;
DROP TABLE IF EXISTS lokasi;

-- Recreate tables with auto-increment reset
CREATE TABLE lokasi (
                        id BIGINT(20) NOT NULL AUTO_INCREMENT,
                        nama_lokasi VARCHAR(255) NOT NULL,
                        negara VARCHAR(255) NOT NULL,
                        provinsi VARCHAR(255) NOT NULL,
                        kota VARCHAR(255) NOT NULL,
                        created_at TIMESTAMP NOT NULL DEFAULT current_timestamp(),
                        PRIMARY KEY (id)
);

CREATE TABLE proyek (
                        id BIGINT(20) NOT NULL AUTO_INCREMENT,
                        nama_proyek VARCHAR(255) NOT NULL,
                        client VARCHAR(255) NOT NULL,
                        tgl_mulai DATETIME NOT NULL,
                        tgl_selesai DATETIME NOT NULL,
                        pimpinan_proyek VARCHAR(255) NOT NULL,
                        keterangan VARCHAR(255) DEFAULT NULL,
                        created_at TIMESTAMP NOT NULL DEFAULT current_timestamp(),
                        PRIMARY KEY (id)
);

CREATE TABLE proyek_lokasi (
                               proyek_id BIGINT(20) NOT NULL,
                               lokasi_id BIGINT(20) NOT NULL,
                               PRIMARY KEY (proyek_id, lokasi_id),
                               FOREIGN KEY (proyek_id) REFERENCES proyek(id) ON DELETE CASCADE,
                               FOREIGN KEY (lokasi_id) REFERENCES lokasi(id) ON DELETE CASCADE
);

-- Insert sample locations
INSERT INTO lokasi (nama_lokasi, negara, provinsi, kota) VALUES
                                                             ('Location 1', 'Country A', 'Province X', 'City M'),
                                                             ('Location 2', 'Country B', 'Province Y', 'City N'),
                                                             ('Location 3', 'Country C', 'Province Z', 'City O'),
                                                             ('Location 4', 'Country A', 'Province X', 'City P'),
                                                             ('Location 5', 'Country B', 'Province Y', 'City Q'),
                                                             ('Location 6', 'Country C', 'Province Z', 'City R'),
                                                             ('Location 7', 'Country A', 'Province X', 'City S'),
                                                             ('Location 8', 'Country B', 'Province Y', 'City T'),
                                                             ('Location 9', 'Country C', 'Province Z', 'City U'),
                                                             ('Location 10', 'Country A', 'Province X', 'City V');

-- Insert sample projects
INSERT INTO proyek (nama_proyek, client, tgl_mulai, tgl_selesai, pimpinan_proyek, keterangan) VALUES
                                                                                                  ('Project 1', 'Client A', '2024-01-01 00:00:00', '2024-06-30 23:59:59', 'Leader 1', 'Description 1'),
                                                                                                  ('Project 2', 'Client B', '2024-02-01 00:00:00', '2024-07-30 23:59:59', 'Leader 2', 'Description 2'),
                                                                                                  ('Project 3', 'Client C', '2024-03-01 00:00:00', '2024-08-30 23:59:59', 'Leader 3', 'Description 3'),
                                                                                                  ('Project 4', 'Client D', '2024-04-01 00:00:00', '2024-09-30 23:59:59', 'Leader 4', 'Description 4'),
                                                                                                  ('Project 5', 'Client E', '2024-05-01 00:00:00', '2024-10-30 23:59:59', 'Leader 5', 'Description 5'),
                                                                                                  ('Project 6', 'Client F', '2024-06-01 00:00:00', '2024-11-30 23:59:59', 'Leader 6', 'Description 6'),
                                                                                                  ('Project 7', 'Client G', '2024-07-01 00:00:00', '2024-12-30 23:59:59', 'Leader 7', 'Description 7'),
                                                                                                  ('Project 8', 'Client H', '2024-08-01 00:00:00', '2025-01-30 23:59:59', 'Leader 8', 'Description 8'),
                                                                                                  ('Project 9', 'Client I', '2024-09-01 00:00:00', '2025-02-28 23:59:59', 'Leader 9', 'Description 9'),
                                                                                                  ('Project 10', 'Client J', '2024-10-01 00:00:00', '2025-03-31 23:59:59', 'Leader 10', 'Description 10');

-- Insert sample project-location associations
INSERT INTO proyek_lokasi (proyek_id, lokasi_id) VALUES
                                                     (1, 1), (1, 2), (1, 3),
                                                     (2, 4), (2, 5), (2, 6),
                                                     (3, 7), (3, 8), (3, 9),
                                                     (4, 10), (4, 1), (4, 2),
                                                     (5, 3), (5, 4), (5, 5),
                                                     (6, 6), (6, 7), (6, 8),
                                                     (7, 9), (7, 10), (7, 1),
                                                     (8, 2), (8, 3), (8, 4),
                                                     (9, 5), (9, 6), (9, 7),
                                                     (10, 8), (10, 9), (10, 10);
