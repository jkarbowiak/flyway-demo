CREATE USER FLYWAY_OWNER IDENTIFIED BY FLYWAY_OWNER;
CREATE USER FLYWAY_APP IDENTIFIED BY FLYWAY_APP;

GRANT CONNECT, RESOURCES, DBA TO FLYWAY_OWNER;
GRANT CONNECT, RESOURCES TO FLYWAY_APP;