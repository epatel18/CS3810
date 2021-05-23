-- CS3810 - Principles of Database Systems - Spring 2021
-- Instructor: Thyago Mota
-- Description: job_skills database script
-- Student(s) Name(s): Eesha Patel

-- database creation and use

CREATE DATABASE IF NOT EXISTS JobSkills;
DROP USER IF EXISTS 'job_skills'@'%';
DROP USER IF EXISTS 'job_skills_admin'@'%';
USE JobSkills;

-- tables creation

CREATE TABLE IF NOT EXISTS Jobs (
    id INTEGER PRIMARY KEY NOT NULL,
    jobTitle VARCHAR(300) NOT NULL
);

CREATE TABLE IF NOT EXISTS Skills (
    skillId INTEGER PRIMARY KEY NOT NULL,
    skillName VARCHAR(300) NOT NULL
);

CREATE TABLE IF NOT EXISTS job_skills (
    job_id INTEGER NOT NULL,
    skill_id INTEGER NOT NULL,
    CONSTRAINT pk_job_skills PRIMARY KEY (job_id, skill_id),
    FOREIGN KEY (job_id) REFERENCES Jobs (id),
    FOREIGN KEY (skill_id) REFERENCES skills (skillId)
);


-- data access

CREATE USER 'job_skills'@'%';
GRANT SELECT ON Jobs.* TO 'job_skills'@'%';
CREATE USER 'job_skills_admin'@'%';
GRANT ALL ON *.* TO 'job_skills_admin'@'%';

-- queries

-- a) what is the total number of job positions?
SELECT COUNT(id) AS 'Total number of job positions' 
FROM Jobs;

-- b) what is the total number of skills?
SELECT COUNT(skillId) AS 'Total number of skills' 
FROM Skills;

-- c) which job position titles have the word "database"?
SELECT jobTitle AS 'Job position titles with the word database' 
FROM Jobs 
WHERE jobtitle LIKE '%database%'; 

-- d) provide an alphabetical list of all job position titles that require "sql" or "mysql" as a skill.
SELECT j.jobTitle 
FROM Jobs j
INNER JOIN job_skills js ON j.id = js.job_id
INNER JOIN skills s ON js.skill_id = s.id
WHERE s.name in ('sql', 'mysql')
ORDER BY 1;

-- e) which skills "database analyst" like positions have that "database admin" like positions don't?
SELECT an.skills AS "Database analyst skills that database admin don't have" FROM(
SELECT s.name AS 'skills' FROM skills s
INNER JOIN job_skills js ON s.id = js.skill_id
JOIN jobs j ON j.id = js.job_id
WHERE title LIKE '%database analyst%') an
WHERE an.skills NOT IN(
SELECT s.name AS 'skills' FROM skills s
INNER JOIN job_skills js ON s.id = js.skill_id
JOIN jobs j ON j.id = js.job_id
WHERE title LIKE '%database admin%')
GROUP BY an.skills
ORDER BY an.skills;

-- f) list the top 20 skills required by job positions having the word "database" in their titles.
SELECT s2.name AS 'Skills'
FROM job_skills js
INNER JOIN skills s2 ON js.skill_id = s2.id
INNER JOIN jobs j ON js.job_id = j.id AND j.title LIKE '%database%'
GROUP BY js.skill_id
ORDER BY js.skill_id
LIMIT 20;

SELECT * FROM Skills;
