-- Drop the zoom table if it exists
DROP TABLE IF EXISTS zoom;

-- Create the zoom table schema
CREATE TABLE zoom (
                      id INT PRIMARY KEY,
                      meeting_id VARCHAR(255),
                      topic VARCHAR(255),
                      start_time TIMESTAMP,
                      duration INT,
                      host_id INT,
                      participant_count INT
);

-- Drop the zoom_archive table if it exists
DROP TABLE IF EXISTS zoom_archive;

-- Create the zoom_archive table schema
CREATE TABLE zoom_archive (
                              id INT PRIMARY KEY,
                              meeting_id VARCHAR(255),
                              topic VARCHAR(255),
                              start_time TIMESTAMP,
                              duration INT,
                              host_id INT,
                              participant_count INT
);

-- Insert test data into the zoom table
INSERT INTO zoom (id, meeting_id, topic, start_time, duration, host_id, participant_count) VALUES
                                                                                               (1, 'meeting1', 'Topic 1', '2024-04-10 09:00:00', 60, 1, 10),
                                                                                               (2, 'meeting2', 'Topic 2', '2024-04-10 10:00:00', 30, 2, 5),
                                                                                               (3, 'meeting3', 'Topic 3', '2024-04-10 11:00:00', NULL, 1, 8),
                                                                                               (4, 'meeting4', 'Topic 4', '2024-04-10 12:00:00', 45, 3, 12),
                                                                                               (5, 'meeting5', 'Topic 5', '2024-04-10 13:00:00', NULL, 2, 6);
